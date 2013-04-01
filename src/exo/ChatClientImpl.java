package exo;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.net.InetAddress;

public class ChatClientImpl implements ChatClientOp{

	@Override
	public void update(Message msg) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println(msg.getSource()+":"+msg.getContent());
	}

public static void main(final String[] args)  throws Exception{

		if(args.length != 2){
			System.err.println("Usage : java "+ChatClientImpl.class.getName()+" Host StubName");
			System.exit(1);
		}

		Registry rmiRegister = LocateRegistry.getRegistry(args[0]);
		ChatServerOp myLocalServerStub = (ChatServerOp) rmiRegister.lookup(args[1]);

		ChatClientImpl currentClient=new ChatClientImpl();
		ChatClientOp clientStub = (ChatClientOp) UnicastRemoteObject.exportObject(currentClient,0); // Doesn't work without 0. Reason : NA

		myLocalServerStub.register(clientStub);

		Scanner sc = new Scanner(System.in);
		String msg = "";

		while(true){
			if(msg.equals("quit")){
				break;
			}
			msg.clean();
			msg = sc.nextLine();
			MyMessage myMessage = new MyMessage(InetAddress.getLocalHost().getHostName()
			,msg);
			myLocalServerStub.write(MyMessage);
		}
	}
}