package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

public interface UserInvDBDAO {
    long addUserInv(String username , String cards);
    String getUserInv(String user);
}
