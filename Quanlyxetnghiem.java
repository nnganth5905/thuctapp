/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quanlyxetnghiem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ngan Ngan
 */
public class Quanlyxetnghiem {
    private static Scanner scanner = new Scanner(System.in);
    private static Connection conn;

 public static void main(String[] args) {
      try {
            // Đăng ký driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Thông tin kết nối
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Nhanvien;encrypt=true;trustServerCertificate=true";
            String userName = "sa";
            String password = "aloaloaloalo"; 
            
            // Thiết lập kết nối
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Ket noi database thanh cong!");
            
            // Hiển thị menu chính
            hienThiMenuChinh();
            
        } catch (ClassNotFoundException e) {
            System.err.println("Loi: khong tim thay");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Loi ket noi database: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Đóng kết nối khi kết thúc
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Da dong ket noi database");
                } catch (SQLException e) {
                    System.err.println("Loi khi dong ket noi: " + e.getMessage());
                }
            }
        }
    }
        private static void hienThiMenuChinh() {  
        while (true) {  
            System.out.println("\n===== HE THONG QUAN LY PHONG XET NGHIEM =====");
            System.out.println("1. Quan ly xet nghiem");
            System.out.println("2. Quan ly nhan vien");
            System.out.println("3. Quan ly khach hang");
            System.out.println("4. Quan ly ket qua xet nghiem");
            System.out.println("5. Thoat");
            System.out.println("Nhap lua chon cua ban");
            
            int Choice = scanner.nextInt();
            scanner.nextLine();
   
            switch(Choice){
                case 1:
                    quanLyXetNghiem();
                    break;
                case 2:
                    quanLyNhanVien();
                    break;
                case 3:
                    quanLyKhachHang();
                    break;
                case 4:
                    quanLyKetQuaXetNghiem();
                case 5:
                    System.out.println("Thoat chuong trinh");
                    System.exit(Choice);
                default:
                    System.out.println("Lua chon khong hop le!");
            }    
     }
 }
       
    private static void quanLyXetNghiem(){
            while (true) {    
                System.out.println("\n===== QUAN LY XET NGHIEM =====");
                System.out.println("1. Them xet nghiem");
                System.out.println("2. Sua xet nghiem");
                System.out.println("3. Xoa xet nghiem");
                System.out.println("4. Hien thi danh sach xet nghiem");
                System.out.println("5.Tim kiem ");
                System.out.println("6. Quay lai");
                System.out.println("Chon chuc nang: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice){
                    case 1:
                        themXetNghiem(); 
                        break;
                    case 2:
                         suaXetNghiem();
                    break;
                case 3:
                    xoaXetNghiem();
                    break;
                case 4:
                    hienThiDanhSachXetNghiem();
                    break;
                case 5:
                    timKiemXetNghiem();
                    break;
                case 6:
                    
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
                
        }
            }
        private static void themXetNghiem(){
        try {
            System.out.print("Nhap ma xet nghiem: ");
            String maXetNghiem = scanner.nextLine();
            System.out.print("Nhap ten xet nghiem: ");
            String tenXetNghiem = scanner.nextLine();
            System.out.print("Nhap loai xet nghiem: ");
            String loai = scanner.nextLine();
            System.out.print("Nhap gia xet nghiem: ");
            double gia = scanner.nextDouble();
            scanner.nextLine();

            String sql = "INSERT INTO XetNghiemTest (MaXetNghiem, tenXetNghiem, loai, gia) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maXetNghiem);
            pstmt.setString(2, tenXetNghiem);
            pstmt.setString(3, loai);
            pstmt.setDouble(4, gia);
            pstmt.executeUpdate();
            System.out.println("Them xet nghiem thanh cong!");
            } catch (SQLException e) {
            System.err.println("Loi khi them xet nghiem: " + e.getMessage());
        }
    }
    private static void suaXetNghiem() {
        
        System.out.print("Nhap ma xet nghiem can sua: ");
        String maXetNghiem = scanner.nextLine();

        try {
            String checkSql = "SELECT * FROM XetNghiemTest WHERE MaXetNghiem = ?";
            PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
            checkPstmt.setString(1, maXetNghiem);
            ResultSet rs = checkPstmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Khong tim thay xet nghiem voi ma: " + maXetNghiem);
                return;
            }

            System.out.println("\nThông tin hiện tại:");
            System.out.println("Ma xet nghiem: " + rs.getString("MaXetNghiem"));
            System.out.println("Ten: " + rs.getString("TenXetNghiem"));
            System.out.println("Loai: " + rs.getString("loai"));
            System.out.println("Gia: " + rs.getDouble("gia"));
            System.out.println("\nNhap thong tin moi (Bo trong neu khong thay doi):");
            System.out.print("Nhap ten xet nghiem moi: ");
            String tenXetNghiem = scanner.nextLine();
            System.out.print("Nhap loai xet nghiem moi: ");
            String loai = scanner.nextLine();
            System.out.print("Nhap gia xet nghiem moi: ");
            String giaInput = scanner.nextLine();

            String sql = "UPDATE XetNghiemTest SET tenXetNghiem = ?, loai = ?, gia = ? WHERE MaXetNghiem = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tenXetNghiem.isEmpty() ? rs.getString("tenXetNghiem") : tenXetNghiem);
            pstmt.setString(2, loai.isEmpty() ? rs.getString("loai") : loai);
            pstmt.setDouble(3, giaInput.isEmpty() ? rs.getDouble("gia") : Double.parseDouble(giaInput));
            pstmt.setString(4, maXetNghiem);
            pstmt.executeUpdate();
            System.out.println("Sua xet nghiem thanh cong!");
        } catch (SQLException e) {
            System.err.println("Loi khi sua xet nghiem: " + e.getMessage());
        }
    }
    private static void xoaXetNghiem() {
       
        System.out.print("Nhap ma xet nghiem can xoa: ");
        String maXetNghiem = scanner.nextLine();

        try {
            String checkSql = "SELECT * FROM XetNghiemTest WHERE MaXetNghiem = ?";
            PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
            checkPstmt.setString(1, maXetNghiem);
            ResultSet rs = checkPstmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Khong tim thay xet nghiem voi ma: " + maXetNghiem);
                return;
            }

            System.out.println("\nThong tin xet nghiem se xoa:");
            System.out.printf("%-10s %-30s %-20s %-10.2f\n",
                    rs.getString("MaXetNghiem"), rs.getString("TenXetNghiem"), rs.getString("Loai"), rs.getDouble("Gia"));

            System.out.print("\nBan co chac muon xoa? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                String sql = "DELETE FROM XetNghiemTest WHERE  MaXetNghiem = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, maXetNghiem);
                pstmt.executeUpdate();
                System.out.println("Da xoa xet nghiem thanh cong!");
            } else {
                System.out.println("Da huy thao tac xoa.");
            }
        } catch (SQLException e) {
            System.err.println("Loi khi xoa xet nghiem: " + e.getMessage());
        }
    }
   

    private static void hienThiDanhSachXetNghiem() {
         System.out.println("\n===== DANH SACH XET NGHIEM =====");
       System.out.printf("%-10s %-30s %-20s %-10s\n", "Ma xet nghiem", "Ten xet nghiem", "Loai", "Gia");
        System.out.println("----------------------------------------------------------------------------");

        try {
            String sql = "SELECT * FROM XetNghiemTest";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                 System.out.printf("%-10s %-30s %-20s %-10.2f\n",
                    rs.getString("MaXetNghiem"), 
                    rs.getString("TenXetNghiem"), 
                    rs.getString("Loai"), 
                    rs.getDouble("Gia"));
            count++;
            }
            System.out.println("Tong so xet nghiem: " + count);
        } catch (SQLException e) {
            System.err.println("Loi khi hien thi danh sach xet nghiem: " + e.getMessage());
        }
    }
    private static void timKiemXetNghiem(){
            System.out.println("\n===== TIM KIEM XET NGHIEM =====");
        System.out.print("Nhap keyword can tim: ");
        String keyword = scanner.nextLine().toLowerCase();

        try {
            String sql = "SELECT * FROM XetNghiemTest WHERE MaXetNghiem LIKE ? OR TenXetNghiem LIKE ? OR Loai LIKE ? OR CAST(Gia AS VARCHAR) LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            String searchTerm = "%" + keyword + "%";
            for (int i = 1; i <= 4; i++) {
                pstmt.setString(i, searchTerm);
            }
            ResultSet rs = pstmt.executeQuery();

                System.out.println("\n===== KET QUA TIM KIEM =====");
                System.out.printf("%-10s %-30s %-20s %-10s\n", "Ma xet nghiem", "Ten xet nghiem", "Loai", "Gia");
                System.out.println("----------------------------------------------------------------------------");
                int count = 0;
        while (rs.next()) {
            System.out.printf("%-10s %-30s %-20s %-10.2f\n",
                    rs.getString("MaXetNghiem"), 
                    rs.getString("TenXetNghiem"), 
                    rs.getString("Loai"), 
                    rs.getDouble("Gia"));
            count++;
        }
        if (count == 0) {
            System.out.println("Khong co xet nghiem trung khop: " + keyword);
        } else {
            System.out.println("Tim thay " + count + " Trung voi xet nghiem");
        }
    } catch (SQLException e) {
        System.err.println("Loi tim xet nghiem: " + e.getMessage());
    }
}
   
    private static void quanLyNhanVien(){
        while (true) {            
            System.out.println("\n===== QUAN LY NHAN VIEN =====");
            System.out.println("1. Them nhan vien");
            System.out.println("2. Sua nhan vien");
            System.out.println("3. Xoa nhan vien");
            System.out.println("4. Hien thi danh sach nhan vien");
            System.out.println("5.Tim kiem");
            System.out.println("6. Quay lai");
            System.out.println("Chon chuc nang:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    themNhanVien();
                    break;
                case 2:
                    suaNhanVien();
                    break;
                case 3:
                    xoaNhanVien();
                    break;
                case 4:
                    hienThiDanhSachNhanVien();
                    break;
                case 5:
                    timKiemNhanVien();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
        
    }
    public enum Role {
    BACSI, DIEUDUONGVIEN, LETAN
}
    private static void themNhanVien(){
       try {
            System.out.print("Nhap ma nhan vien: ");
            String maNhanVien = scanner.nextLine();
            System.out.print("Nhap ten nhan vien: ");
            String tenNhanVien = scanner.nextLine();
            System.out.print("Nhap gioi tinh: ");
            String gioiTinh = scanner.nextLine();
            System.out.print("Nhap so dien thoai: ");
            String soDienThoai = scanner.nextLine();

            System.out.print("Nhap vi tri (BACSI, LETAN, DIEUDUONGVIEN): ");
            String viTriStr = scanner.nextLine().trim().toUpperCase();
            Role viTri = Role.valueOf(viTriStr);

            String sql = "INSERT INTO testthu (MaNhanVien, TenNhanVien, GioiTinh, SoDienThoai, ViTri) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maNhanVien);
            pstmt.setString(2, tenNhanVien);
            pstmt.setString(3, gioiTinh);
            pstmt.setString(4, soDienThoai);
            pstmt.setString(5, viTri.toString());
            pstmt.executeUpdate();
            System.out.println("Them nhan vien thanh cong!");
        } catch (IllegalArgumentException e) {
            System.out.println("Vi tri khong hop le! Vui long nhap BACSI, LETAN hoac DIEUDUONGVIEN.");
        } catch (SQLException e) {
            System.err.println("Loi khi them nhan vien: " + e.getMessage());
        }
    }
    private static void suaNhanVien(){
        System.out.print("Nhap ma nhan vien can sua: ");
        String maNhanVien = scanner.nextLine();

        try {
            String checkSql = "SELECT * FROM testthu WHERE MaNhanVien = ?";
            PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
            checkPstmt.setString(1, maNhanVien);
            ResultSet rs = checkPstmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Khong tim thay nhan vien voi ma: " + maNhanVien);
                return;
            }

            System.out.print("Nhap ten moi (bo trong neu giu nguyen): ");
            String tenNhanVien = scanner.nextLine();
            System.out.print("Nhap gioi tinh moi: ");
            String gioiTinh = scanner.nextLine();
            System.out.print("Nhap so dien thoai moi: ");
            String soDienThoai = scanner.nextLine();
            System.out.print("Nhap vi tri moi (BACSI, LETAN, DIEUDUONGVIEN): ");
            String viTriStr = scanner.nextLine().trim().toUpperCase();

            String sql = "UPDATE testthu SET maNhanVien = ?, gioiTinh = ?, soDienThoai = ?, viTri = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tenNhanVien.isEmpty() ? rs.getString("TenNhanVien") : tenNhanVien);
            pstmt.setString(2, gioiTinh.isEmpty() ? rs.getString("gioiTinh") : gioiTinh);
            pstmt.setString(3, soDienThoai.isEmpty() ? rs.getString("soDienThoai") : soDienThoai);
            pstmt.setString(4, viTriStr.isEmpty() ? rs.getString("viTri") : Role.valueOf(viTriStr).toString());
            pstmt.setString(5, maNhanVien);
            pstmt.executeUpdate();
            System.out.println("Sua nhan vien thanh cong!");
        } catch (IllegalArgumentException e) {
            System.out.println("Vi tri khong hop le! Vui long nhap BACSI, LETAN hoac DIEUDUONGVIEN.");
        } catch (SQLException e) {
            System.err.println("Loi khi sua nhan vien: " + e.getMessage());
        }
    }
    private static void xoaNhanVien() {
       
        System.out.print("Nhap ma nhan vien can xoa: ");
        String maNhanVien = scanner.nextLine();

        try {
            String checkSql = "SELECT * FROM testthu WHERE MaNhanVien = ?";
            PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
            checkPstmt.setString(1, maNhanVien);
            ResultSet rs = checkPstmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Khong tim thay nhan vien voi ID: " + maNhanVien);
                return;
            }

            System.out.println("\nThong tin nhan vien se xoa:");
            System.out.printf("%-12s %-30s %-10s %-15s %-15s\n",
                    rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getString("gioiTinh"), rs.getString("soDienThoai"), rs.getString("viTri"));

            System.out.print("\nBan co chac muon xoa? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                String sql = "DELETE FROM testthu WHERE MaNhanVien = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, maNhanVien);
                pstmt.executeUpdate();
                System.out.println("Da xoa nhan vien thanh cong!");
            } else {
                System.out.println("Da huy thao tac xoa.");
            }
        } catch (SQLException e) {
            System.err.println("Loi khi xoa nhan vien: " + e.getMessage());
        }
    }
        private static void hienThiDanhSachNhanVien() {
        System.out.println("\n===== DANH SACH NHAN VIEN =====");
        System.out.printf("%-12s %-30s %-10s %-15s %-15s\n", "Ma nhan vien", "Ten nhan vien", "Gioi tinh", "So dien thoai", "Vi tri");
        System.out.println("----------------------------------------------------------------------------");

        try {
            String sql = "SELECT * FROM testthu";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                System.out.printf("%-12s %-30s %-10s %-15s %-15s\n",
                        rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getString("gioiTinh"), rs.getString("soDienThoai"), rs.getString("viTri"));
                count++;
            }
            System.out.println("Tong so nhan vien: " + count);
        } catch (SQLException e) {
            System.err.println("Loi khi hien thi danh sach nhan vien: " + e.getMessage());
        }
    }
    
    private static void timKiemNhanVien(){
       System.out.println("\n===== TIM KIEM NHAN VIEN =====");
        System.out.print("Nhap keyword can tim: ");
        String keyword = scanner.nextLine().toLowerCase();

        try {
            String sql = "SELECT * FROM testthu WHERE MaNhanVien LIKE ? OR TenNhanVien LIKE ? OR GioiTinh LIKE ? OR soDienThoai LIKE ? OR viTri LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            String searchTerm = "%" + keyword + "%";
            for (int i = 1; i <= 5; i++) {
                pstmt.setString(i, searchTerm);
            }
            ResultSet rs = pstmt.executeQuery();

         
        System.out.println("\n===== KET QUA TIM KIEM =====");
        System.out.printf("%-12s %-30s %-10s %-15s %-15s\n", 
                "MaNhanVien", "TenNhanVien", "GioiTinh", "SoDienThoai", "ViTri");
        System.out.println("----------------------------------------------------------------------------");
        
        int count = 0;
        while (rs.next()) {
            System.out.printf("%-12s %-30s %-10s %-15s %-15s\n",
                    rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), 
                    rs.getString("GioiTinh"), rs.getString("SoDienThoai"), 
                    rs.getString("ViTri"));
            count++;
        }
        
        if (count == 0) {
            System.out.println("Khong tim thay nhan vien nào phu hop voi tu khoa: " + keyword);
        } else {
            System.out.println("Tim thay " + count + " ket qua phu hop");
        }
    } catch (SQLException e) {
        System.err.println("Loi khi tim kiem nhan vien: " + e.getMessage());
    }
}
       
       private static void quanLyKhachHang() {
        while (true) {
            System.out.println("\n===== QUAN LY KHACH HANG =====");
            System.out.println("1. Them khach hang");
            System.out.println("2. Sua khach hang");
            System.out.println("3. Xoa khach hang");
            System.out.println("4. Hien thi danh sach khach hang");
            System.out.println("5.Tim kiem");
            System.out.println("6. Quay lai");
            System.out.print("Chon chuc nang: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    themKhachHang();
                    break;
                case 2:
                    suaKhachHang();
                    break;
                case 3:
                    xoaKhachHang();
                    break;
                case 4:
                    hienThiDanhSachKhachHang();
                    break;
                case 5:
                    timKiemKhachHang();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
       private static void themKhachHang() {
        try {
            System.out.print("Nhap ma khach hang: ");
            String maKhachHang = scanner.nextLine();
            System.out.print("Nhap ten khach hang: ");
            String tenKhachHang = scanner.nextLine();
            System.out.print("Nhap ngay sinh: ");
            String ngaySinh = scanner.nextLine();
            System.out.print("Nhap so dien thoai: ");
            String soDienThoai = scanner.nextLine();
            System.out.print("Nhap gioi tinh: ");
            String gioiTinh = scanner.nextLine();

            String sql = "INSERT INTO khachhang (MaKhachHang, TenKhachHang, ngaySinh, soDienThoai, gioiTinh) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maKhachHang);
            pstmt.setString(2, tenKhachHang);
            pstmt.setString(3, ngaySinh.isEmpty() ? null : ngaySinh); 
            pstmt.setString(4, soDienThoai);
            pstmt.setString(5, gioiTinh);
            pstmt.executeUpdate();
            System.out.println("Them khach hang thanh cong!");
            } catch (SQLException e) {
            System.err.println("Loi khi them khach hang: " + e.getMessage());
        }
       }

       private static void suaKhachHang() {
       System.out.print("Nhap ma khach hang can sua: ");
        String maKhachHang = scanner.nextLine();

        try {
            String checkSql = "SELECT * FROM khachhang WHERE MaKhachHang = ?";
            PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
            checkPstmt.setString(1, maKhachHang);
            ResultSet rs = checkPstmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Khong tim thay khach hang theo ID: " + maKhachHang);
                return;
            }

            System.out.print("Nhap ten moi (bo trong neu giu nguyen): ");
            String tenKhachHang = scanner.nextLine();
            System.out.print("Nhap ngay sinh moi(yyyy-MM-dd): ");
            String ngaySinh = scanner.nextLine();
            System.out.print("Nhap so dien thoai moi: ");
            String soDienThoai = scanner.nextLine();
            System.out.print("Nhap gioi tinh moi: ");
            String gioiTinh = scanner.nextLine();
            String sql = "UPDATE khachhang SET TenKhachHang = ?, ngaySinh = ?, soDienThoai = ?, gioiTinh = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tenKhachHang.isEmpty() ? rs.getString("tenKhachHang") : tenKhachHang);
            pstmt.setString(2, ngaySinh.isEmpty() ? rs.getString("ngaySinh") : ngaySinh);
            pstmt.setString(3, soDienThoai.isEmpty() ? rs.getString("soDienThoai") : soDienThoai);
            pstmt.setString(4, gioiTinh.isEmpty() ? rs.getString("gioiTinh") : gioiTinh);
            pstmt.setString(5, maKhachHang);
            pstmt.executeUpdate();
            System.out.println("Sua khach hang thanh cong!");
        } catch (SQLException e) {
            System.err.println("Loi khi sua khach hang: " + e.getMessage());
        }
    }
    private static void xoaKhachHang() {
        System.out.print("Nhap ma khach hang can xoa: ");
        String maKhachHang = scanner.nextLine();

        try {
            String checkSql = "SELECT * FROM khachhang WHERE MaKhachHang = ?";
            PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
            checkPstmt.setString(1, maKhachHang);
            ResultSet rs = checkPstmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Khong tim thay khach hang theo ma: " + maKhachHang);
                return;
            }

            System.out.println("\nThong tin khach hang muon xoa:");
            System.out.printf("%-12s %-25s %-12s %-15s %-10s\n",
                    rs.getString("MaKhachHang"), rs.getString("TenNhanVien"), rs.getString("ngaySinh"), rs.getString("soDienThoai"), rs.getString("gioiTinh"));

            System.out.print("\nBan co chac muon xoa? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                String sql = "DELETE FROM khachhang WHERE MaKhachHang = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, maKhachHang);
                pstmt.executeUpdate();
                System.out.println("Da xoa khach hang thanh cong!");
            } else {
                System.out.println("Da huy thao tac xoa.");
            }
        } catch (SQLException e) {
            System.err.println("Loi khi xoa khach hang: " + e.getMessage());
        }
    }

    private static void hienThiDanhSachKhachHang() {
        System.out.println("\n===== DANH SACH KHACH HANG =====");
        System.out.printf("%-12s %-25s %-12s %-15s %-10s\n", "MaKhachHang", "TenKhachHang", "Ngay sinh", "So dien thoai", "Gioi tinh");
        System.out.println("----------------------------------------------------------------------------");

        try {
            String sql = "SELECT * FROM khachhang";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                 System.out.printf("%-12s %-25s %-12s %-15s %-10s\n",
                        rs.getString("MaKhachHang"), rs.getString("TenKhachHang"), rs.getString("ngaySinh"), rs.getString("soDienThoai"), rs.getString("gioiTinh"));
                count++;
            }
            System.out.println("Tong so khach hang: " + count);
        } catch (SQLException e) {
            System.err.println("Loi khi hien thi danh sach khach hang: " + e.getMessage());
        }
    }
   private static void timKiemKhachHang() {
    System.out.println("\n===== TIM KIEM KHACH HANG =====");
        System.out.print("Nhap keyword can tim: ");
        String keyword = scanner.nextLine().toLowerCase();

        try {
            String sql = "SELECT * FROM KhachHang WHERE MaKhachHang LIKE ? OR TenKhachHang LIKE ? OR CONVERT(VARCHAR, NgaySinh, 120) LIKE ? OR SoDienThoai LIKE ? OR GioiTinh LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            String searchTerm = "%" + keyword + "%";
            for (int i = 1; i <= 5; i++) {
                pstmt.setString(i, searchTerm);
            }
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n===== KẾT QUẢ TÌM KIẾM =====");
        System.out.printf("%-12s %-25s %-12s %-15s %-10s\n",
                "Ma khach hang", "Ten khach hang", "Ngay sinh", "So dien thoai", "Gioi tinh");
        System.out.println("----------------------------------------------------------------------------");
        
        int count = 0;
        while (rs.next()) {
            System.out.printf("%-12s %-25s %-12s %-15s %-10s\n",
                    rs.getString("MaKhachHang"), rs.getString("TenKhachHang"),
                    rs.getDate("NgaySinh"), rs.getString("SoDienThoai"),
                    rs.getString("GioiTinh"));
            count++;
        }
        
        if (count == 0) {
            System.out.println("Không tìm thấy khách hàng nào phù hợp với từ khóa: " + keyword);
        } else {
            System.out.println("Tìm thấy " + count + " kết quả phù hợp");
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi tìm kiếm khách hàng: " + e.getMessage());
    }
}
   private static void quanLyKetQuaXetNghiem() {
    while (true) {
        System.out.println("\n===== QUAN LY KET QUA XET NGHIEM (KetQuaXetNghiem) =====");
        System.out.println("1. Them ket qua xet nghiem");
        System.out.println("2. Sua ket qua xet nghiem");
        System.out.println("3. Xoa ket qua xet nghiem");
        System.out.println("4. Hien thi danh sach ket qua xet nghiem");
        System.out.println("5. Tim kiem");
        System.out.println("6. Quay lai");
        System.out.print("Chon chuc nang: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: themKetQuaXetNghiem(); break;
            case 2: suaKetQuaXetNghiem(); break;
            case 3: xoaKetQuaXetNghiem(); break;
            case 4: hienThiDanhSachKetQuaXetNghiem(); break;
            case 5: timKiemKetQuaXetNghiem(); break;
            case 6: return;
            default: System.out.println("Lua chon khong hop le!");
        }
    }
}

private static void themKetQuaXetNghiem() {
    try {
        System.out.print("Nhap Ma Ket Qua: ");
        String maKetQua = scanner.nextLine();
        System.out.print("Nhap Ma Xet Nghiem: ");
        String maXetNghiem = scanner.nextLine();
        System.out.print("Nhap Ma Khach Hang: ");
        String maKhachHang = scanner.nextLine();
        System.out.print("Nhap Ma Nhan Vien: ");
        String maNhanVien = scanner.nextLine();
        System.out.print("Nhap Thoi Gian Xet Nghiem (yyyy-MM-dd HH:mm:ss): ");
        String thoiGianXetNghiem = scanner.nextLine();
        System.out.print("Nhap Thoi Gian Tra Ket Qua (yyyy-MM-dd HH:mm:ss): ");
        String thoiGianTraKetQua = scanner.nextLine();
        System.out.print("Nhap Ket Qua: ");
        String ketQua = scanner.nextLine();
        System.out.print("Nhap Trang Thai: ");
        String trangThai = scanner.nextLine();

        String sql = "INSERT INTO KetQuaXetNghiem (MaKetQua, MaXetNghiem, MaKhachHang, MaNhanVien, ThoiGianXetNghiem, ThoiGianTraKetQua, KetQua, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, maKetQua);
        pstmt.setString(2, maXetNghiem);
        pstmt.setString(3, maKhachHang);
        pstmt.setString(4, maNhanVien);
        pstmt.setString(5, thoiGianXetNghiem);
        pstmt.setString(6, thoiGianTraKetQua);
        pstmt.setString(7, ketQua);
        pstmt.setString(8, trangThai);
        pstmt.executeUpdate();
        System.out.println("Them ket qua xet nghiem thanh cong!");
    } catch (SQLException e) {
        System.err.println("Loi khi them ket qua xet nghiem: " + e.getMessage());
    }
}

private static void suaKetQuaXetNghiem() {
    System.out.print("Nhap Ma Ket Qua can sua: ");
    String maKetQua = scanner.nextLine();

    try {
        String checkSql = "SELECT * FROM KetQuaXetNghiem WHERE MaKetQua = ?";
        PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
        checkPstmt.setString(1, maKetQua);
        ResultSet rs = checkPstmt.executeQuery();

        if (!rs.next()) {
            System.out.println("Khong tim thay ket qua xet nghiem voi MaKetQua: " + maKetQua);
            return;
        }

        System.out.println("\nThong tin hien tai:");
        System.out.printf("%-10s %-15s %-15s %-15s %-20s %-20s %-30s %-10s\n",
                rs.getString("MaKetQua"), rs.getString("MaXetNghiem"), rs.getString("MaKhachHang"),
                rs.getString("MaNhanVien"), rs.getString("ThoiGianXetNghiem"), rs.getString("ThoiGianTraKetQua"),
                rs.getString("KetQua"), rs.getString("TrangThai"));

        System.out.print("Nhap Ma Xet Nghiem moi (bo trong neu giu nguyen): ");
        String maXetNghiem = scanner.nextLine();
        System.out.print("Nhap Ma Khach Hang moi: ");
        String maKhachHang = scanner.nextLine();
        System.out.print("Nhap Ma Nhan Vien moi: ");
        String maNhanVien = scanner.nextLine();
        System.out.print("Nhap Thoi Gian Xet Nghiem moi (yyyy-MM-dd HH:mm:ss): ");
        String thoiGianXetNghiem = scanner.nextLine();
        System.out.print("Nhap Thoi Gian Tra Ket Qua moi (yyyy-MM-dd HH:mm:ss): ");
        String thoiGianTraKetQua = scanner.nextLine();
        System.out.print("Nhap Ket Qua moi: ");
        String ketQua = scanner.nextLine();
        System.out.print("Nhap Trang Thai moi: ");
        String trangThai = scanner.nextLine();

        String sql = "UPDATE KetQuaXetNghiem SET MaXetNghiem = ?, MaKhachHang = ?, MaNhanVien = ?, ThoiGianXetNghiem = ?, ThoiGianTraKetQua = ?, KetQua = ?, TrangThai = ? WHERE MaKetQua = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, maXetNghiem.isEmpty() ? rs.getString("MaXetNghiem") : maXetNghiem);
        pstmt.setString(2, maKhachHang.isEmpty() ? rs.getString("MaKhachHang") : maKhachHang);
        pstmt.setString(3, maNhanVien.isEmpty() ? rs.getString("MaNhanVien") : maNhanVien);
        pstmt.setString(4, thoiGianXetNghiem.isEmpty() ? rs.getString("ThoiGianXetNghiem") : thoiGianXetNghiem);
        pstmt.setString(5, thoiGianTraKetQua.isEmpty() ? rs.getString("ThoiGianTraKetQua") : thoiGianTraKetQua);
        pstmt.setString(6, ketQua.isEmpty() ? rs.getString("KetQua") : ketQua);
        pstmt.setString(7, trangThai.isEmpty() ? rs.getString("TrangThai") : trangThai);
        pstmt.setString(8, maKetQua);
        pstmt.executeUpdate();
        System.out.println("Sua ket qua xet nghiem thanh cong!");
    } catch (SQLException e) {
        System.err.println("Loi khi sua ket qua xet nghiem: " + e.getMessage());
    }
}

private static void xoaKetQuaXetNghiem() {
    System.out.print("Nhap Ma Ket Qua can xoa: ");
    String maKetQua = scanner.nextLine();

    try {
        String checkSql = "SELECT * FROM KetQuaXetNghiem WHERE MaKetQua = ?";
        PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
        checkPstmt.setString(1, maKetQua);
        ResultSet rs = checkPstmt.executeQuery();

        if (!rs.next()) {
            System.out.println("Khong tim thay ket qua xet nghiem voi MaKetQua: " + maKetQua);
            return;
        }

        System.out.println("\nThong tin ket qua xet nghiem se xoa:");
        System.out.printf("%-10s %-15s %-15s %-15s %-20s %-20s %-30s %-10s\n",
                rs.getString("MaKetQua"), rs.getString("MaXetNghiem"), rs.getString("MaKhachHang"),
                rs.getString("MaNhanVien"), rs.getString("ThoiGianXetNghiem"), rs.getString("ThoiGianTraKetQua"),
                rs.getString("KetQua"), rs.getString("TrangThai"));

        System.out.print("\nBan co chac muon xoa? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            String sql = "DELETE FROM KetQuaXetNghiem WHERE MaKetQua = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maKetQua);
            pstmt.executeUpdate();
            System.out.println("Da xoa ket qua xet nghiem thanh cong!");
        } else {
            System.out.println("Da huy thao tac xoa.");
        }
    } catch (SQLException e) {
        System.err.println("Loi khi xoa ket qua xet nghiem: " + e.getMessage());
    }
}

private static void hienThiDanhSachKetQuaXetNghiem() {
    System.out.println("\n===== DANH SACH KET QUA XET NGHIEM (KetQuaXetNghiem) =====");
    System.out.printf("%-10s %-15s %-15s %-15s %-20s %-20s %-30s %-10s\n",
            "MaKetQua", "MaXetNghiem", "MaKhachHang", "MaNhanVien", "ThoiGianXN", "ThoiGianTraKQ", "KetQua", "TrangThai");
    System.out.println("------------------------------------------------------------------------------------------------------------");

    try {
        String sql = "SELECT * FROM KetQuaXetNghiem";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        int count = 0;
        while (rs.next()) {
            System.out.printf("%-10s %-15s %-15s %-15s %-20s %-20s %-30s %-10s\n",
                    rs.getString("MaKetQua"), rs.getString("MaXetNghiem"), rs.getString("MaKhachHang"),
                    rs.getString("MaNhanVien"), rs.getString("ThoiGianXetNghiem"), rs.getString("ThoiGianTraKetQua"),
                    rs.getString("KetQua"), rs.getString("TrangThai"));
            count++;
        }
        System.out.println("Tong so ket qua xet nghiem: " + count);
    } catch (SQLException e) {
        System.err.println("Loi khi hien thi danh sach ket qua xet nghiem: " + e.getMessage());
    }
}

        private static void timKiemKetQuaXetNghiem() {
   
        System.out.println("\n===== TIM KIEM KET QUA XET NGHIEM =====");
        System.out.print("Nhap keyword can tim: ");
        String keyword = scanner.nextLine();
        String searchPattern = "%" + keyword + "%";

        try {
        String sql = "SELECT * FROM KetQuaXetNghiem WHERE " +
                    "MaKetQua LIKE ? OR " +
                    "MaXetNghiem LIKE ? OR " +
                    "MaKhachHang LIKE ? OR " +
                    "MaNhanVien LIKE ? OR " +
                    "CONVERT(VARCHAR, ThoiGianXetNghiem, 120) LIKE ? OR " +
                    "CONVERT(VARCHAR, ThoiGianTraKetQua, 120) LIKE ? OR " +
                    "KetQua LIKE ? OR " +
                    "TrangThai LIKE ?";
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        String searchTerm = "%" + keyword + "%";
        // 4. Thiết lập tham số
        for (int i = 1; i <= 8; i++) {
            pstmt.setString(i, searchPattern);
        }
        ResultSet rs = pstmt.executeQuery();

        // 6. Hiển thị kết quả
        System.out.println("\n===== KET QUA TIM KIEM =====");
        System.out.printf("%-10s %-12s %-12s %-12s %-20s %-20s %-30s %-10s\n", 
                "MaKQ", "MaXN", "MaKH", "MaNV", "ThoiGianXN", "ThoiGianTra", "KetQua", "TrangThai");
        System.out.println("--------------------------------------------------------------------------------------------");
        
        int count = 0;
        while (rs.next()) {
            count++;
            System.out.printf("%-10s %-12s %-12s %-12s %-20s %-20s %-30s %-10s\n",
                    rs.getString("MaKetQua"),
                    rs.getString("MaXetNghiem"),
                    rs.getString("MaKhachHang"),
                    rs.getString("MaNhanVien"),
                    rs.getTimestamp("ThoiGianXetNghiem"),
                    rs.getTimestamp("ThoiGianTraKetQua"),
                    rs.getString("KetQua"),
                    rs.getString("TrangThai"));
        }
        
        if (count == 0) {
            System.out.println("Khong co ket qua trung khop: " + keyword);
        } else {
            System.out.println("Tim thay" + count + " ket qua phu hop");
        }
    } catch (SQLException e) {
        System.err.println("Loi khi dong ket noi: " + e.getMessage());
    }
}
}
       