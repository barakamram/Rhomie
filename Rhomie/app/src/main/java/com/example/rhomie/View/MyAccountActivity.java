package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.rhomie.Controller.IMyAccountController;
import com.example.rhomie.Controller.MyAccountController;
import com.example.rhomie.Objects.Item;
import com.example.rhomie.R;
import java.util.ArrayList;

public class MyAccountActivity extends AppCompatActivity implements IMyAccountView{
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private IMyAccountController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        listView = findViewById(R.id.requestListView);

        controller = new MyAccountController(this);
        controller.getItems();
    }

    @Override
    public void drawItems(ArrayList<Item> items) {

        ArrayList<String> itemsS = new ArrayList<>();
        for(Item item:items){
            itemsS.add(item.itemToString(true));
        }
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, itemsS);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setClickable(true);

//-------------------------------------------------------------------------------//

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MyAccountActivity.this, MyItemActivity.class);
                i.putExtra("item_id",items.get(position).getItem());
//                i.putExtra("user_id",items.get(position).getFatherID());
                i.putExtra("address",items.get(position).getAddress().addressToString());
                i.putExtra("check_in",items.get(position).getCheckIn());
                i.putExtra("check_out",items.get(position).getCheckOut());
                i.putExtra("guest_number",items.get(position).getGuestNumber());
                i.putExtra("flags",items.get(position).getFlags().flagsToString());

                i.putExtra("city", items.get(position).getAddress().getCity());
                i.putExtra("street", items.get(position).getAddress().getStreet());
                i.putExtra("street_number", items.get(position).getAddress().getStreetNumber());
                i.putExtra("floor", items.get(position).getAddress().getFloor());
                i.putExtra("apartment_number", items.get(position).getAddress().getApartmentNumber());

                i.putExtra("kosher",items.get(position).getFlags().getKosher());
                i.putExtra("animals",items.get(position).getFlags().getAnimals());
                i.putExtra("accessibility",items.get(position).getFlags().getAccessibility());
                i.putExtra("parking",items.get(position).getFlags().getParking());
                i.putExtra("smoking",items.get(position).getFlags().getSmoking());
                i.putExtra("wi_fi",items.get(position).getFlags().getWiFi());
//                if(items.get(position).isIs_switcher())
//                    i.putExtra("item_details",items.get(position).)


                startActivity(i);

            }
        });
    }



    @Override
    public void getItemSuccess(String massage) {
        Toast.makeText(MyAccountActivity.this, massage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getItemError(String massage) {
        Toast.makeText(MyAccountActivity.this, massage, Toast.LENGTH_SHORT).show();
    }

    public void goToAddItem(View view) {
        startActivity(new Intent(MyAccountActivity.this, AddItemActivity.class));
    }

    public void goToHomePage(View view) {
        startActivity(new Intent(MyAccountActivity.this, HomeActivity.class));
    }
}