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
	requires javafx.graphics;
	requires java.desktop;
	
	exports GUI;
	exports common;
	exports GUI.control;

	opens common to javafx.base;
	opens GUI to javafx.fxml;
}