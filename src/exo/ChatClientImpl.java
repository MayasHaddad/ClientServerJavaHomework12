package exo;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


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
		PersonManager myLocalStub = (PersonManager) rmiRegister.lookup(args[1]);

		Client currentClient = new Client(myLocalStub);

		Scanner sc = new Scanner(System.in);
		String msg = "";
		char cmd;

}
