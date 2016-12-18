package com.example.blueproject1;

import java.util.ArrayList;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class DeviceActivity extends Activity {

	private static final String TAG = "MainActivity";
	BluetoothAdapter adapter = null;
	
	ListView list = null;
	ArrayList<MyItem> data = new ArrayList<MyItem>();
	MyAdapter adapter1 = null;
	
	View.OnClickListener bHandler  = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.button1 :
				doDiscovery();
				break;
			}
			
		}
	};
	void doDiscovery(){		// 블루투스 검색 검색중이면 종료하고 다시 검색!
		if(adapter.isDiscovering()){
			adapter.cancelDiscovery();
		}
		adapter.startDiscovery();
	}
	
	Set<BluetoothDevice> devices = null;
	
	BroadcastReceiver receiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			
			if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){
				Log.v(TAG, "주변장비 검색 완료");
				// 검색이 끝났을 경우!
			}else if(action.equals(BluetoothDevice.ACTION_FOUND)){
				BluetoothDevice device 
					= intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				
				Log.v(TAG, "이름 : " + device.getName());
				
				doAddItem(device);
				// 리스트에 등록 후
				adapter1.notifyDataSetChanged();
				// 갱신을 해줘야 보인다.
			}
			
		}
		
	};
	
	
	@Override
	protected void onPause() {
		unregisterReceiver(receiver);
		super.onPause();
	} // 리시버를 끝낸다.
	
	@Override
	protected void onResume() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		filter.addAction(BluetoothDevice.ACTION_FOUND);
		
		registerReceiver(receiver, filter);
		super.onResume();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.device);
	    
	    list = (ListView)findViewById(R.id.listView1);
	    findViewById(R.id.button1).setOnClickListener(bHandler);
	    
	    adapter = BluetoothAdapter.getDefaultAdapter();
	    devices =  adapter.getBondedDevices();
	    
	    doShowList();
	    // 기존에 페어링 목록은 사전에 미리 등록을 하고,
	    
	    adapter1 = new MyAdapter(this, R.layout.item, data);
	    
	    list.setAdapter(adapter1);
	    
	    if(adapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE){
	    	Intent dIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
	    	dIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
	    	startActivity(dIntent);
	    }
	    // 기기를 스캔해서 방송으로 보내 이러면 위에 Recevier에 가겠지!?
	    findViewById(R.id.button1).setOnClickListener(bHandler);
	}
	void doShowList(){
		
		for(BluetoothDevice device : devices){
			Log.v(TAG, "주소 : " + device.getAddress() + 
					" 이름 : " + device.getName());
			
			doAddItem(device);
		}
	}
	
	void doAddItem(BluetoothDevice device){
		MyItem item = new MyItem();
		item.setAddress(device.getAddress());
		item.setName(device.getName());
		data.add(item);
	}
	

}
