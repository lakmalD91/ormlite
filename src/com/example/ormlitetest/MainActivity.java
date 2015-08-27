package com.example.ormlitetest;

import java.util.List;

import com.example.data.RepoItem;
import com.example.data.RepoUsers;
import com.example.models.Item;
import com.example.models.User;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	RepoUsers repo;
	RepoItem itemrepo;
	TextView txtOutput;
	Button initButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtOutput = (TextView) findViewById(R.id.textView1);
        initButton = (Button) findViewById(R.id.button1);
        
        repo = RepoUsers.initRepo(this);
        itemrepo = RepoItem.initRepo(this);
        
        initButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				//Creating a User
				User us1 = new User("Dayadra", "log", "pwd", "email");
				//save or update user
				repo.create(us1);
				
				//List down saved users
				List<User> usr = repo.getAll();
				for (User user : usr) {
					txtOutput.append("\n"+user.getAlias()+":"+user.getUsername());
				}
				
				txtOutput.append("\n");
				txtOutput.append("\n");
				
				//creating an item
				Item it1 = new Item("garlic bread","bread",150.00);
				//saving an item
				itemrepo.create(it1);
				
				//List down items			
				List<Item> its = itemrepo.getAll();
				for (Item item : its) {
					txtOutput.append("\n"+item.getItemname()+":"+item.getItemprice());
				}
				
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
