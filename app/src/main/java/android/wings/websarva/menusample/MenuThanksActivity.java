package android.wings.websarva.menusample;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MenuThanksActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_thanks);

        //先の画面で生成したインテントオブジェクトを取得
        Intent intent = getIntent();

        //先のデータから渡されたデータの取得し、変数に代入
        String menuName = intent.getStringExtra("menuName");
        String menuPrice = intent.getStringExtra("menuPrice");

        //上で取得した定食名と金額を表示させるtextViewを取得
        TextView tvMenuName = findViewById(R.id.tvMenuName);
        TextView tvMenuPrice = findViewById(R.id.tvMenuPrice);

        //取得したtextViewに定食名と金額を表示
        tvMenuName.setText(menuName);
        tvMenuPrice.setText(menuPrice);

        //アクションバーを取得
        ActionBar actionBar = getSupportActionBar();
        //アクションバーの「戻る」メニューを表示
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //選択されたメニューのidを取得
        int itemId = item.getItemId();
        //選択されたメニューが「戻る」の場合、アクティビティを終了
        if(itemId == android.R.id.home){
            finish();
        }
        //親クラスの同名メソッドを呼び出し、その戻り値を返す
        return super.onOptionsItemSelected(item);
    }

}


