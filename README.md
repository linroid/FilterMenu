# FilterMenu
This is a library project with a custom view that implements  concept of  Filter Menu(https://dribbble.com/shots/1956586-Filter-Menu) made by  Anton Aheichanka for android

## Quick Overview
 - Download sample [Apk](sample/sample-release.apk)
 - Screenshots
    ![Screenshot1](screenshots/device-2015-03-12-215100.png)
 - Youtube Video
 
     [![Youtube Video](http://img.youtube.com/vi/HClK9Ams6gM/0.jpg)](http://www.youtube.com/watch?v=HClK9Ams6gM)
     
## Futures
 - Easy to set it's position
 - Detect edge automatically
## Getting Started
 - Download the source to use it as library project.
 - Declare FilterMenuLayout inside your layout
 
    ```xml
    <com.linroid.filtermenu.library.FilterMenuLayout
        android:id="@+id/filter_menu"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:elevation="8dp"
        custom:expandedRadius="96dp"
        custom:collapsedRadius="24dp"
        custom:centerBottom="50dp"
        custom:centerRight="50dp"
        custom:primaryColor="#ff37aa4a"
        custom:primaryDarkColor="#ff20622b">
    </com.linroid.filtermenu.library.FilterMenuLayout>
    
    <!--circle radius size when menu expanded-->
    custom:expandedRadius 
    <!--circle radius size when menu collapsed-->
    custom:collapsedRadius
    <!--set the position of circle, the menu will auto align.
        You should only set two directions at most.-->
    custom:center[Left|Top|Right|Bottom]
    <!--primary color-->
    custom:primaryColor
    <!--color of inner circle when menu expanded-->
    custom:primaryDarkColor
    ```
 - Initial menu items via java code
 
    ```java
    FilterMenuLayout layout = (FilterMenuLayout) findViewById(R.id.filter_menu);
    FilterMenu menu = new FilterMenu.Builder(this)
        .addItem(R.drawable...)
        .addItem(R.drawable...)
        .attach(layout)
        .withListener(new FilterMenu.OnMenuChangeListener() {
            @Override
            public void onMenuItemClick(View view, int position) {
            }
            @Override
            public void onMenuCollapse() {
            }
            @Override
            public void onMenuExpand() {
            }
        })
        .build();
    ```
    
## License
Copyright 2015 linroid

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

