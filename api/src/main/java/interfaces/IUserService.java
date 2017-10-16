package interfaces;

import entity.User;


public interface IUserService {
    User createUser();
    User findByUserName();
    void removeUser(int idUser);

}
