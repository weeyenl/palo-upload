package com.ianlau.paloupload.upload.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TB_PALO_ORDER")
public class TbPaloOrder {

	 @Id
	 @GeneratedValue(generator = "uuid")
	 @GenericGenerator(name = "uuid", strategy = "uuid2")
	 private String id;

	 private String region;

	 private String country;
	 
	 private String itemType;
	 
	 private String salesChannel;
	 
	 private String orderPriority;
	 
	 private String orderDate;
	 
	 private String orderID;
	 
	 private String shipDate;
	 
	 private String unitsSold;
	 
	 private String unitPrice;
	 
	 private String unitCost;
	 
	 private String totalRevenue;
	 
	 private String totalCost;
	 
	 private String totalProfit;
	 
	 private String nric;

	 
	 public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public TbPaloOrder() {
		 
	 }
	 
	public TbPaloOrder(String id, String region, String country, String itemType, String salesChannel,
			String orderPriority, String orderDate, String orderID, String shipDate, String unitsSold, String unitPrice,
			String unitCost, String totalRevenue, String totalCost, String totalProfit) {
		super();
		this.id = id;
		this.region = region;
		this.country = country;
		this.itemType = itemType;
		this.salesChannel = salesChannel;
		this.orderPriority = orderPriority;
		this.orderDate = orderDate;
		this.orderID = orderID;
		this.shipDate = shipDate;
		this.unitsSold = unitsSold;
		this.unitPrice = unitPrice;
		this.unitCost = unitCost;
		this.totalRevenue = totalRevenue;
		this.totalCost = totalCost;
		this.totalProfit = totalProfit;
	}
	
	public TbPaloOrder(final String[] data) {
		super();
		this.region = data[0];
		this.country = data[1];
		this.itemType = data[2];
		this.salesChannel = data[3];
		this.orderPriority = data[4];
		this.orderDate = data[5];
		this.orderID = data[6];
		this.shipDate = data[7];
		this.unitsSold = data[8];
		this.unitPrice = data[9];
		this.unitCost = data[10];
		this.totalRevenue = data[11];
		this.totalCost = data[12];
		this.totalProfit = data[13];
	 }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

	public String getOrderPriority() {
		return orderPriority;
	}

	public void setOrderPriority(String orderPriority) {
		this.orderPriority = orderPriority;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public String getUnitsSold() {
		return unitsSold;
	}

	public void setUnitsSold(String unitsSold) {
		this.unitsSold = unitsSold;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(String unitCost) {
		this.unitCost = unitCost;
	}

	public String getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(String totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	public String getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(String totalProfit) {
		this.totalProfit = totalProfit;
	}
	 
	 
	 
	  
}
