package com.cruds.db;

import com.cruds.model.*;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {

    public boolean add(Product product) {
        String sql = "insert into product(id, name, category, quantity) values(?, ?, ?, ?)";
        int rows = 0;

        try (Connection conn = DBConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, product.getId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getCategory());
            ps.setInt(4, product.getQuantity());

            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows > 0;
    }

    public boolean addSupplier(Supplier supplier) {
        String sql = "insert into supplier(name, email, product_id) values(?, ?, ?)";
        int rows = 0;

        try (Connection conn = DBConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getEmail());
            ps.setInt(3, supplier.getProduct_id());

            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows > 0;
    }

    public boolean addCustomer(Customer customer) {
        String sql = "insert into customer(id, name) values(?, ?)";
        int rows = 0;

        try (Connection conn = DBConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customer.getId());
            ps.setString(2, customer.getName());

            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows >= 0;
    }

    public boolean customerExist(Customer customer) {
        String sql = "select id, name from customer where id = ?";
        boolean flag = false;
        ;

        try (Connection conn = DBConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customer.getId());
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public DefaultTableModel getCustomerById(int id) {
        String sql = "select id, name from customer where id = ?";
        Vector<String> colNames = new Vector<>();
        colNames.add("ID");
        colNames.add("Name");

        Vector<Vector<String>> data = new Vector<>();

        try (Connection conn = DBConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(data, colNames);
    }

    public DefaultTableModel getCustomerByName(String name) {
        String sql = "select id, name from customer where LOWER(name) = ?";
        Vector<String> colNames = new Vector<>();
        colNames.add("ID");
        colNames.add("Name");

        Vector<Vector<String>> data = new Vector<>();

        try (Connection conn = DBConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name.toLowerCase());

            ResultSet rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(data, colNames);
    }

    public DefaultTableModel getByName(String name) {
        String sql = "select b.id, b.name, b.category, b.quantity, a.name from product b, supplier a where a.product_id = b.id and LOWER(b.name) like ? ";
        Vector<String> colNames = new Vector<>();
        colNames.add("Product ID");
        colNames.add("Product Name");
        colNames.add("Category");
        colNames.add("Quantity");
        colNames.add("Supplier");

        Vector<Vector<String>> data = new Vector<>();

        try (Connection conn = DBConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(String.valueOf(rs.getInt(4)));
                row.add(rs.getString(5));
                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(data, colNames);
    }

    public DefaultTableModel getByCategory(String category) {
        String sql = "select b.id, b.name, b.category, b.quantity, a.name from product b, supplier a where a.product_id = b.id and LOWER(b.category) like ?";
        Vector<String> colNames = new Vector<>();
        colNames.add("Product ID");
        colNames.add("Product Name");
        colNames.add("Category");
        colNames.add("Quantity");
        colNames.add("Supplier");

        Vector<Vector<String>> data = new Vector<>();

        try (Connection conn = DBConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + category.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();

            while (rs != null && rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(String.valueOf(rs.getInt(4)));
                row.add(rs.getString(5));
                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(data, colNames);
    }

    public DefaultTableModel getBySupplier(String name) {
        String sql = "select b.id, b.name, b.category, b.quantity, a.name from product b, supplier a where b.id = a.product_id and LOWER(a.name) like ?";
        Vector<String> colNames = new Vector<>();
        colNames.add("Product ID");
        colNames.add("Product Name");
        colNames.add("Category");
        colNames.add("Quantity");
        colNames.add("Supplier");

        Vector<Vector<String>> data = new Vector<>();

        try (Connection conn = DBConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();

            while (rs != null && rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(String.valueOf(rs.getInt(4)));
                row.add(rs.getString(5));
                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(data, colNames);
    }

    public DefaultTableModel getById(int id) {
        String sql = "select b.id, b.name, b.category, b.quantity, a.name from product b, supplier a where a.product_id = b.id and LOWER(b.id) = ?";
        Vector<String> colNames = new Vector<>();
		colNames.add("Product ID");
		colNames.add("Product Name");
		colNames.add("Category");
		colNames.add("Quantity");
		colNames.add("Supplier");

        Vector<Vector<String>> data = new Vector<>();

        try (Connection conn = DBConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs != null && rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(String.valueOf(rs.getInt(4)));
                row.add(rs.getString(5));
                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(data, colNames);
    }

    public DefaultTableModel getTableProductSupplier() {
        String sql = "select b.id, b.name, b.category, b.quantity, a.name from product b, supplier a where a.product_id = b.id";
        Vector<String> colNames = new Vector<>();
		colNames.add("Product ID");
		colNames.add("Product Name");
		colNames.add("Category");
		colNames.add("Quantity");
		colNames.add("Supplier");

        Vector<Vector<String>> data = new Vector<>();

        try (Connection conn = DBConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs != null && rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(String.valueOf(rs.getInt(4)));
                row.add(rs.getString(5));
                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(data, colNames);
    }

    public boolean orderProduct(Order order) {
        String sql = "insert into product_order(id, issue_date, product_id) values(?, ?, ?)";
        String sqlCount = "update product set quantity = quantity - 1 where id = ?";
        int rows = 0;
        int rowsCount = 0;
        java.sql.Date idate = new java.sql.Date(order.getIssue_date().getTime());

        try (Connection conn = DBConnectionManager.getConnection()) {
            PreparedStatement psCount = conn.prepareStatement(sqlCount);
            psCount.setInt(1, order.getProduct_id());
            rowsCount = psCount.executeUpdate();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, order.getId());
            ps.setDate(2, idate);
            ps.setInt(3, order.getProduct_id());
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows > 0 && rowsCount > 0;
    }

    public DefaultTableModel listBookByUsn(String usn) {
        String sql = "select bi.issue_id, b.book_title, bi.usn, s.name, bi.issue_date, bi.return_date, bi.book_isbn  from book b, student s, book_issue bi where b.book_isbn = bi.book_isbn and bi.usn = s.usn and LOWER(bi.usn) = ?";
        Vector<String> colNames = new Vector<>();
        colNames.add("ID");
        colNames.add("Product Name");
        colNames.add("ID");
        colNames.add("Student Name");
        colNames.add("Issue Date");
        colNames.add("Return Date");
        colNames.add("ID");

        Vector<Vector<String>> data = new Vector<>();

        try (Connection conn = DBConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usn);
            ResultSet rs = ps.executeQuery();

            while (rs != null && rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(String.valueOf(rs.getInt(1)));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(String.valueOf(rs.getDate(5)));
                row.add(String.valueOf(rs.getDate(6)));
                row.add(rs.getString(7));
                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new DefaultTableModel(data, colNames);
    }

    public DefaultTableModel listIssuedBooks() {
        String sql = "select bi.order_id, b.product_name, s.name, bi.issue_date, bi.book_isbn  from product b, customer s, product_order bi where b.product_id = bi.product_id";
        Vector<String> colNames = new Vector<>();
        colNames.add("ID");
        colNames.add("Product Name");
        colNames.add("Customer Name");
        colNames.add("Issue Date");

        Vector<Vector<String>> data = new Vector<>();

        try (Connection conn = DBConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs != null && rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(String.valueOf(rs.getInt(1)));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(String.valueOf(rs.getDate(5)));
                row.add(String.valueOf(rs.getDate(6)));
                row.add(rs.getString(7));
                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new DefaultTableModel(data, colNames);
    }

    public String[] getAllCategory() {
        String sql = "Select unique(category) from product";
        List<String> list = new ArrayList<>();
        list.add("Select");
        try (Connection conn = DBConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while (rs != null && rs.next()) {
                list.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] category = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            category[i] = list.get(i);
        }

        return category;
    }


}
