package exo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientOp extends Remote {
void update(Message msg) throws RemoteException;
}