package com.example.liquerstore;

import javafx.scene.paint.Paint;
import org.kordamp.ikonli.javafx.FontIcon;

public class CustomIcon extends FontIcon {
    public CustomIcon(IconTypeEnum iconType) {
        switch (iconType){
            case ADD_ICON: {
                this.setIconLiteral("bi-plus");
                break;
            }
            case DELETE_ICON: {
                this.setIconLiteral("bi-trash");
                break;
            }
            case REFRESH_ICON: {
                this.setIconLiteral("bi-arrow-clockwise");
                break;
            }
            case EDIT_ICON: {
                this.setIconLiteral("bi-pencil-square");
                break;
            }
        }
        this.setIconColor(Paint.valueOf("#fff"));
    }
}

