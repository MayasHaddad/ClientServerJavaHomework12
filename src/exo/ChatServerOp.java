package exo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerOp extends Remote{

	void register(ChatClientOp client) throws RemoteException; // Mets le stub recu dans une liste
	
	public void unregister(ChatClientOp client) throws RemoteException; // supprime le stub de la liste
	
	public void write (Message msg) throws RemoteException; // ecris sur les stubs clients
	
}
