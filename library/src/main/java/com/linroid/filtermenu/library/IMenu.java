package com.linroid.filtermenu.library;

/**
 * Created by linroid on 15/3/9.
 */
public interface IMenu {
    void collapse(boolean animate);
    void expand(boolean animate);
    void toggle(boolean animate);
    void setMenuLayout(FilterMenuLayout layout);
}
