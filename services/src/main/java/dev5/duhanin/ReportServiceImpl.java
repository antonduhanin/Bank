package dev5.duhanin;

import dev5.duhanin.entity.Role;
import dev5.duhanin.interfaces.ReportService;
import dev5.duhanin.repository.IRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportServiceImpl implements ReportService {

    @Autowired
    private IRoleDAO roleDAO;

    public List<Role> roleList() {
        return roleDAO.findAll();
    }
}
