import { Component, ViewChild, ElementRef } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { UploadService } from './upload/service/upload.service';
import { HttpResponse, HttpEventType } from '@angular/common/http';
import { merge, Observable, of as observableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { DataService } from './data/service/data.service';

export interface UserData {
  id: string;
  name: string;
  progress: string;
  color: string;
}

const COLORS: string[] = [
  'maroon', 'red', 'orange', 'yellow', 'olive', 'green', 'purple', 'fuchsia', 'lime', 'teal',
  'aqua', 'blue', 'navy', 'black', 'gray'
];
const NAMES: string[] = [
  'Maia', 'Asher', 'Olivia', 'Atticus', 'Amelia', 'Jack', 'Charlotte', 'Theodore', 'Isla', 'Oliver',
  'Isabella', 'Jasper', 'Cora', 'Levi', 'Violet', 'Arthur', 'Mia', 'Thomas', 'Elizabeth'
];

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {
  title = 'PaloUI';

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  dataSource;
  pageSize = 10;
  pageSizeOptions: number[] = [5, 10, 30, 50];

  displayedColumns: string[] = ['region', 'country', 'itemType', 'salesChannel', 'orderPriority', 'orderDate', 'orderID', 'shipDate', 'unitsSold', 'unitPrice', 'unitCost'
  , 'totalRevenue', 'totalCost', 'totalProfit', 'nric'];

  @ViewChild('fileInput') fileInput: ElementRef;
  fileAttr = 'Choose File';

  selectedFile;
  progress: { percentage: number } = { percentage: 0 };
  uploadInProgress = false;

  isLoadingResults = true;
  resultsLength = 0;

  responseMsg;

  constructor(private uploadService: UploadService, private dataService: DataService) {
  }

  // ngAfterViewInit() {
  //   this.dataSource.paginator = this.paginator;
  //   this.dataSource.sort = this.sort;
  // }

  ngAfterViewInit() {
    this.refreshTable();
  }

  refreshTable(){
    // If the user changes the sort order, reset back to the first page.
    this.sort.active = "region";
    this.paginator.pageIndex = 0;
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.dataService.getData(
            this.sort.active, this.sort.direction, this.paginator.pageIndex, this.paginator.pageSize);
        }),
        map(data => {
          // Flip flag to show that loading has finished.
          this.isLoadingResults = false;
          this.resultsLength = data.totalCount;

          return data.items;
        }),
        catchError(() => {
          this.isLoadingResults = false;

          return observableOf([]);
        })
      ).subscribe(data => this.dataSource = data);
  }



  uploadFileEvt(imgFile: any) {
    if (imgFile.target.files && imgFile.target.files[0]) {
      this.fileAttr = '';
      Array.from(imgFile.target.files).forEach((file: File) => {
        this.fileAttr += file.name;
      });


      this.selectedFile = imgFile.target.files[0];

      this.fileInput.nativeElement.value = "";
    } else {
      this.fileAttr = 'Choose File';
    }
  }

  upload() {
    this.progress.percentage = 0;
    this.isLoadingResults = true;
    if (!this.selectedFile) {
      alert('Selected file is empty!');
      return;
    }


    this.uploadService.pushFileToStorage(this.selectedFile).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        alert('File Successfully Uploaded');
        this.isLoadingResults = false;
        this.responseMsg = event.body;
        this.refreshTable();
      }

      this.selectedFile = undefined;
      this.fileAttr = 'Choose File';
    },
      error => {
        this.isLoadingResults = false;
        alert("Error uploading file. Please upload a CSV file with the correct format.");
      },
    );
  }

}

