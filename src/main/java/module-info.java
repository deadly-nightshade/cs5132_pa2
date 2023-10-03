module me.squiddy.pa2 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens me.squiddy.pa2 to javafx.fxml;
    exports me.squiddy.pa2;
}