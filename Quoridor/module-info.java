module quoridor {
	requires java.desktop;
	requires org.junit.jupiter.api;
	requires javafx.graphics; 
	requires javafx.controls;
	requires javafx.base;
	requires java.management;
	opens quoridor to javafx.graphics;
}