module PhamarcyStore_Nhom1 {
	
//	requires javafx.base;
//	requires javafx.controls;
	requires javafx.fxml;
//	requires javafx.graphics;
//	requires javafx.media;
	requires javafx.swing;
	requires javafx.swt;
	requires javafx.web;
	requires java.sql;
	requires charm.glisten;
	requires org.controlsfx.controls;
	requires javafx.controls;
	requires javafx.base;
	
	exports GUI;
	exports common;
	exports validateField;
	
	opens common to javafx.base;
	opens validateField to javafx.fxml;
}