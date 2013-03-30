package exo;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class ChatServerImpl implements ChatServerOp{

	List<ChatClientOp> clientStubsList;
	Message currentMessage;
	
	@Override
	public void register(ChatClientOp client) throws RemoteException {
		// TODO Auto-generated method stub
		this.clientStubsList.add(client);
	}

	@Override
	public void unregister(ChatClientOp client) throws RemoteException {
		// TODO Auto-generated method stub
		this.clientStubsList.remove(client);
	}

	@Override
	public void write(Message msg) throws RemoteException {
		// TODO Auto-generated method stub
		Iterator<ChatClientOp> lIterator=this.clientStubsList.iterator();
		while(lIterator.hasNext()){
			ChatClientOp currentStub = lIterator.next();
			currentStub.update(msg);
		}
	}
	
	public ChatServerImpl(){
		this.clientStubsList=new LinkedList<ChatClientOp>();
	}
	
	public static void main(final String[] args)  throws Exception{

		if(args.length!=1){
			System.err.println("Usage : java "+ChatServerImpl.class.getName()+" StubName");
			System.exit(1);
		}
		
		ChatServerImpl currentPersonManager=new ChatServerImpl();
		ChatServerOp stub = (ChatServerOp) UnicastRemoteObject.exportObject(currentPersonManager,0); // Doesn't work without 0. Reason : NA
		
		Registry rmiRegister=LocateRegistry.getRegistry();
		rmiRegister.rebind(args[0], stub);
		
		System.out.println("Ready !");
	}
}
