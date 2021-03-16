package android.wings.websarva.menusample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuListActivity extends AppCompatActivity {

    //リストビュー
    private ListView _lvMenu;
    //リストビューに表示するデータ
    private List<Map<String, Object>> _menuList;
    //SimpleAdapter の第４引数fromに使用する定数
    private static final String[] FROM = {"name", "price"};
    //SimpleAdapter の第５引数toに使用する定数
    private static final int[]TO = {R.id.tvMenuName, R.id.tvMenuPrice};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        _lvMenu = findViewById(R.id.lvMenu);
        // リストに表示するリストデータ
        _menuList = createTeishokuList();
        //SimpleAdapterを生成（R.layout.row は独自に作成したレイアウト）
        SimpleAdapter adapter = new SimpleAdapter(MenuListActivity.this, _menuList, R.layout.row, FROM, TO);

        _lvMenu.setAdapter(adapter);
        _lvMenu.setOnItemClickListener(new ListItemClickListener());

        // コンテキストメニューを表示させる画面部品の登録
        registerForContextMenu(_lvMenu);
    }

    //menulistを作成メソッド
    //priceを数値にするため、Mapのバリューは Object型。
    private List<Map<String, Object>> createTeishokuList() {
        //Listオブジェクト生成
        List<Map<String, Object>> menuList = new ArrayList<>();

        //Listに格納するデータを作成、追加していく
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "唐揚げ定食");
        menu.put("price", 800);
        menu.put("desc", "若鶏の唐揚げにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        //ここから上の繰り返し。menu変数を再利用。
        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", 850);
        menu.put("desc", "手ごねハンバーグにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        //ここから繰り返し。
        menu = new HashMap<>();
        menu.put("name", "生姜焼き定食");
        menu.put("price", 850);
        menu.put("desc", "特製ソースの生姜焼きにサラダ、ご飯とお味噌汁がつきます");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ステーキ定食");
        menu.put("price", 1000);
        menu.put("desc", "国産牛のステーキにサラダ、ご飯とお味噌汁がつきます。");

        return menuList;
    }

    //上の定食メニュー作成メソッドと同様
    private List<Map<String, Object>> createCurryList(){

        List<Map<String, Object>> menuList = new ArrayList<>();

        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "ビーフカレー");
        menu.put("price", 520);
        menu.put("desc", "特選スパイスをきかせた国産ビーフ100％のカレーです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ポークカレー");
        menu.put("price", 420);
        menu.put("desc", "特選スパイスをきかせた国産ポーク100％のカレーです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "カツカレー");
        menu.put("price", 700);
        menu.put("desc", "国産豚ヒレ肉を使用したカツカレーです。");
        menuList.add(menu);

        return menuList;
    }

    // オプションメニュー表示
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //メニューインフレーターの取得
        MenuInflater inflater = getMenuInflater();
        //オプションメニュー用xmlファイルをインフレート. inflateメソッドでxmlからオブジェクト生成
        inflater.inflate(R.menu.menu_options_menu_list, menu);
        //親クラスの同名メソッドを呼び出し、その戻り値を返す
        return super.onCreateOptionsMenu(menu);
    }

    // オプションメニュー選択時
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //選択されたメニューのIDを取得
        int itemId = item.getItemId();

        switch(itemId){
            //定食メニュー
            case R.id.menuListOptionsTeishoku:
                _menuList = createTeishokuList();
                break;
            //カレーメニュー
            case R.id.menuListOptionsCurry:
                _menuList = createCurryList();
                break;
        }

        //上で選択したメニューデータでSimpleAdapterを生成
        SimpleAdapter adapter
            = new SimpleAdapter(MenuListActivity.this, _menuList, R.layout.row, FROM, TO);

        _lvMenu.setAdapter(adapter);

        //親クラスの同名メソッドを呼び出し、その戻り値を返す
        return super.onOptionsItemSelected(item);
    }

    // コンテキストメニュー表示
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        // メニューインフレータ取得
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context_menu_list, menu);
        // コンテキストメニューのタイトル設定
        menu.setHeaderTitle(R.string.menu_list_context_header);
    }

    // コンテキストメニュー選択時
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // 長押しされたビューに関する情報が格納されたオブジェクトを取得
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        // 長押しされたリストのポジションを取得し、_menuListからMapオブジェクトを取得
        int listPosition = info.position;
        Map<String, Object> menu = _menuList.get(listPosition);

        // 選択されたメニュー（コンテキストメニュー内）
        int itemId = item.getItemId();

        switch(itemId) {
            case R.id.menuListContextDesc:
                String desc = (String) menu.get("desc");
                Toast.makeText(MenuListActivity.this, desc, Toast.LENGTH_LONG).show();
                break;
            case R.id.menuListContextOrder:
                order(menu);
                break;
        }

        // 親クラスの同盟メソッドを呼び出し、戻り値を返す。
        return super.onContextItemSelected(item);
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //タップされた行のデータ取得。SimpleAdapterでは1行分はMap型。
            Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);

            order(item);

            /*
            //nameキーとpriceキーに入っている値を取得（Mapのバリューobject型のため、キャスト必要）
            String menuName = (String)item.get("name");
            Integer menuPrice = (Integer)item.get("price");

            Intent intent = new Intent(MenuListActivity.this, MenuThanksActivity.class);

            intent.putExtra("menuName", menuName);
            intent.putExtra("menuPrice", menuPrice + "円");

            startActivity(intent);
             */
        }
    }

    private void order(Map<String, Object> menu) {

        // MapのバリューがObject型のためキャストが必要
        String menuName = (String) menu.get("name");
        Integer menuPrice = (Integer) menu.get("price");

        Intent intent = new Intent(MenuListActivity.this, MenuThanksActivity.class);
        intent.putExtra("menuName", menuName);
        intent.putExtra("menuPrice", menuPrice + "円");

        startActivity(intent);
    }
}
