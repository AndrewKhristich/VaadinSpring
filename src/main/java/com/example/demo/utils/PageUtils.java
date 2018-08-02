package com.example.demo.utils;

import com.vaadin.flow.component.UI;

public class PageUtils {

    public static void reloadPage() {
        UI.getCurrent().getPage().reload();
    }

}
