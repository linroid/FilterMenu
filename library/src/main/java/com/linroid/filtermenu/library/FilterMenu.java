package com.linroid.filtermenu.library;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by linroid on 15/3/8.
 */
public class FilterMenu implements IMenu {

    private List<Item> items = new ArrayList<>();
    private OnMenuChangeListener listener;
    private FilterMenuLayout layout;
//    /**
//     * add menu item to layout
//     *
//     * @param item
//     * @param listener
//     */
//    public void addItem(Item item, View.OnClickListener listener) {
//        items.add(item);
//    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public OnMenuChangeListener getListener() {
        return listener;
    }

    public void setListener(OnMenuChangeListener listener) {
        this.listener = listener;
        for (final Item item : getItems()) {
            item.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("FilterMenu", "onClick");
                    if (getListener() != null) {
                        getListener().onMenuItemClick(item.getView(), item.getPosition());
                    }
                    if (layout != null) {
                        layout.collapse(true);
                    }
                }
            });
        }
    }

    @Override
    public void collapse(boolean animate) {
        layout.collapse(animate);
    }

    @Override
    public void expand(boolean animate) {
        layout.expand(animate);
    }

    @Override
    public void toggle(boolean animate) {
        layout.toggle(animate);
    }

    @Override
    public void setMenuLayout(FilterMenuLayout view) {
        this.layout = view;
        if (view == null) {
            return;
        }
        for (final Item item : getItems()) {
            layout.addView(item.getView());
        }
        layout.setMenu(this);
    }

    public static interface OnMenuChangeListener {
        void onMenuItemClick(View view, int position);

        void onMenuCollapse();

        void onMenuExpand();
    }

    public static class Builder {
        OnMenuChangeListener listener;
        private List<Item> items = new ArrayList<>();
        private Context ctx;
        private LayoutInflater inflater;
        private FilterMenuLayout layout;

        public Builder(Context ctx) {
            this.ctx = ctx;
            this.inflater = LayoutInflater.from(ctx);
        }

        public Builder withListener(OnMenuChangeListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder inflate(int menuResId) {
            PopupMenu popupMenu = new PopupMenu(ctx, null);
            popupMenu.inflate(menuResId);
            Menu menu = popupMenu.getMenu();
            for (int i = 0; i < menu.size(); i++) {
                MenuItem item = menu.getItem(i);
                addItem(item.getIcon());
            }
            menu.clear();
            menu = null;
            popupMenu = null;
            return this;
        }

        public Builder addItem(Drawable icon) {
            ImageButton view = (ImageButton) inflater.inflate(R.layout.menu_item, null, false);
            view.setImageDrawable(icon);
//            TypedValue value = new TypedValue();
//            ctx.getTheme().resolveAttribute(R.attr.selectableItemBackgroundBorderless, value, true);
//            view.setBackgroundResource(value.resourceId);
            addItem(view);
            return this;
        }

        public Builder addItem(int iconResId) {
            Drawable icon = ctx.getResources().getDrawable(iconResId);
            addItem(icon);
            return this;
        }

        public Builder addItem(View customView) {
            Item item = new Item();
            item.setView(customView);
            item.setPosition(items.size());
            item.getView().setTag(item);
            items.add(item);

            return this;
        }

        public Builder attach(FilterMenuLayout view) {
            this.layout = view;
            return this;
        }

        public FilterMenu build() {
            FilterMenu menu = new FilterMenu();
            menu.setItems(items);
            menu.setListener(this.listener);
            menu.setMenuLayout(this.layout);
            return menu;
        }
    }

    public static class Item {
        private View view;
        private int x;
        private int y;
        private int position;
        private Rect bounds = new Rect(0, 0, 0, 0);

        public View getView() {
            return view;
        }

        public void setView(View view) {
            this.view = view;
            view.setAlpha(0f);
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public void setBounds(int left, int top, int right, int bottom) {
            this.bounds.set(left, top, right, bottom);
        }

        public Rect getBounds() {
            return bounds;
        }

        public void setBounds(Rect bounds) {
            this.bounds = bounds;
        }
    }
}
