import java.util.ArrayList;
import java.util.List;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
public class GCMServer {

	public GCMServer(){
		String key = "AIzaSyBGjeLlUujKYZZTkgcsmi6FYQ7iQTlvrlQ";
		String regId = "APA91bEqiUyF32pHO4zWgYW_Agq2_ldu5e3_MdoZyhOjoXcCTxVgLVQO_vN_l0Pd0r3ZTCQQmQyV2wZZFRHjhDPDmMI1-4KuImH7GuBJzLhkGwIEEiq8yRmZDSS61FbJ-zDqh1A3HjSfgRJnJuvFf1jILG5mm7GgL9-3Vb8mG0GkQtdh9D-bM9I";
		System.out.println("key : " + key);
		Sender sender = null;
		
		//다중 REGID 값
//		ArrayList<String> list = new ArrayList<String>();
//		list.add("tempregid1");
//		list.add("tempregid2");
//		list.add("tempregid3");
//		
//		MulticastResult multi = null;
//		List<Result> results = null;
		
		try{
			sender = new Sender(key);
		}catch(Exception e){
			System.out.println("sender key error : " + e);
		}

//		Message message = new Message.Builder().addData("code", "1").addData("result", "GCMTest 축하드립리다.").build();
//		Message message = new Message.Builder().addData("code", "2").addData("result", "http://m.naver.com").build();
//		Message message = new Message.Builder().addData("code", "3").addData("result", "37.554644,126.970700").build();
		Message message = new Message.Builder().addData("code", "4").addData("result", "화면 켜고 Main 실행").build();

		
		try{
			//멀티 regId 전송
//			multi = sender.send(message, list, 5);
//			results = multi.getResults();
			
			Result result = sender.send(message, regId, 5);
			if (result.getMessageId() != null) {

				System.out.println("send success");
				String canonicalRegId = result
						.getCanonicalRegistrationId();
				if (canonicalRegId != null) {
					System.out.println("Also updated registration id!");
				}
			} else {
				String error = result.getErrorCodeName();
				if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
					System.out.println("Unregistered device #");
					
				} else {
					System.out.println("Error sending message to device #");
				}
			}
		}catch(Exception e){
			System.out.println(" 전송 에러 : " + e);
		}
	}

	public static void main(String[] args) {
		new GCMServer();
	}
}
