module com.example.f23comp1011s2moviesapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    opens com.example.f23comp1011s2moviesapp to javafx.fxml;
    exports com.example.f23comp1011s2moviesapp;
}