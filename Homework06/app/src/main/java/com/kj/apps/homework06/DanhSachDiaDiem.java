package com.kj.apps.homework06;

import java.util.ArrayList;

/**
 * Created by KJ Mok on 08/04/2017.
 */

public class DanhSachDiaDiem {

    public static ArrayList<DiaDiem> danhSach() {
        ArrayList<DiaDiem> ds = new ArrayList<>();
        ds.add(new DiaDiem("The Coffee House - Lãnh Binh Thăng", "http://thecoffeehouse.vn", 10.764767, 106.647897));
        ds.add(new DiaDiem("Cửa hàng máy tính Phong Vũ - CMT8", "http://phongvu.vn", 10.773843, 106.689661));
        ds.add(new DiaDiem("Nhà sách Nguyễn Văn Cừ - Nguyễn Văn Cừ", "http://nhasachnguyenvancu.com", 10.757954, 106.684602));
        ds.add(new DiaDiem("Circle K Store - Lý Thường Kiệt","https://www.circlek.com.vn",10.775120, 106.657182));
        ds.add(new DiaDiem("FPT Shop - CMT8","http://fptshop.com.vn",10.779455, 106.679051));
        ds.add(new DiaDiem("Điện Máy Xanh - 3 Tháng 2","https://www.dienmayxanh.com",10.777578, 106.682315));
        ds.add(new DiaDiem("Bệnh viện Nhân dân 115 - Sư Vạn Hạnh","http://benhvien115.com.vn",10.774990, 106.668134));
        ds.add(new DiaDiem("Chùa Linh Quy Pháp Ấn", "http://linhquyphapan.vn", 11.416644, 107.823208));
        return ds;
    }
}
