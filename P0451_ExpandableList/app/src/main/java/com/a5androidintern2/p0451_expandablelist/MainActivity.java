package com.a5androidintern2.p0451_expandablelist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class MainActivity extends Activity {

    //названия компаний (групп).
    String[] groups = new String[] {"HTC", "Samsung", "LG"};

    //названия телефонов (элементов).
    String[] phonesHTC = new String[] {"Sensation", "Desire", "Wildfire", "Hero"};
    String[] phonesSams = new String[] {"Galaxy S II", "Galaxy Nexus", "Wave"};
    String[] phonesLG = new String[] {"Optimus", "Optimus Link", "Optimus Black", "Optimus One"};

    //коллекция для групп.
    ArrayList<Map<String, String>> groupData;

    //коллекция для элементов одной группы.
    ArrayList<Map<String, String>> childDataItem;

    //общая коллекция для коллекций элементов.
    ArrayList<ArrayList<Map<String, String>>> childData;
    //в итоге получится childData = ArrayList<childDataItem>

    //список атрибутов группы или элемента.
    Map<String, String> m;

    ExpandableListView elvMain;


    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //заполняем коллекцию групп из массива с названиями групп.
        //каждая группа представляет собой Map<String, String>.
        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups) {
            //заполняем список атрибутов для каждой группы.
            m = new HashMap<String, String>();

            //для каждой группы мы укажем всего лишь один атрибут - имя группы.
            m.put("groupName", group); // имя компании.
            groupData.add(m);
        }

        //список атрибутов групп для чтения
        String groupFrom[] = new String[] {"groupName"};
        //список ID view-элементов, в которые будет помещены считанные значения атрибутов.
        int groupTo[] = new int[] {android.R.id.text1};


        //создаем коллекцию для коллекций элементов
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        //создаем коллекцию элементов для первой группы.
        childDataItem = new ArrayList<Map<String, String>>();
        //заполняем список атрибутов для каждого элемента.
        for (String phone : phonesHTC) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone); // название телефона.
            childDataItem.add(m);
        }
        //добавляем в коллекцию коллекций.
        childData.add(childDataItem);

        //создаем коллекцию элементов для второй группы.
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesSams) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        //создаем коллекцию элементов для третьей группы.
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesLG) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        //список атрибутов элементов для чтения.
        String childFrom[] = new String[] {"phoneName"};
        //список ID view-элементов, в которые будет помещены атрибуты элементов.
        int childTo[] = new int[] {android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,//контекст.
                groupData,//коллекция групп.

                //layout-ресурс для отображения группы в списке.
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,//массив имён атрибутов групп.
                groupTo,//массив id TextView из Layout групп.
                childData,//коллекция коллекций элементов по группам.

                //layout-ресурс для отображения элемента в списке.
                android.R.layout.simple_list_item_1,
                childFrom,//массив имён атрибутов элементов.
                childTo);//массив id TextView из Layout элементов.

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);
    }
}