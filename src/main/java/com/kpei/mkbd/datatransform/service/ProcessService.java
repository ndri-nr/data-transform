package com.kpei.mkbd.datatransform.service;

import com.kpei.mkbd.datatransform.dto.*;
import com.kpei.mkbd.datatransform.util.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProcessService {
    public static void processDataVD(Connection conn, MkbTransformDto dto, LogUtil log) {
        String functionName = "";
        try {
            conn.setAutoCommit(false);

            functionName = "Update Manager Name";
            updateManagerName(conn, dto, log, functionName);

            functionName = "Transformasi VD52";
            insertDataTrVd52(conn, dto, log, functionName);

            functionName = "Transformasi VD53";
            insertDataTrVd53(conn, dto, log, functionName);

            functionName = "Transformasi VD54";
            insertDataTrVd54(conn, dto, log, functionName);

            functionName = "Transformasi VD55";
            insertDataTrVd55(conn, dto, log, functionName);

            functionName = "Transformasi VD56A";
            insertDataTrVd56A(conn, dto, log, functionName);

            functionName = "Transformasi VD56B";
            insertDataTrVd56B(conn, dto, log, functionName);

            functionName = "Transformasi VD56C";
            insertDataTrVd56C(conn, dto, log, functionName);

            functionName = "Transformasi VD57A";
            insertDataTrVd57A(conn, dto, log, functionName);

            functionName = "Transformasi VD57B";
            insertDataTrVd57B(conn, dto, log, functionName);

            functionName = "Transformasi VD57C";
            insertDataTrVd57C(conn, dto, log, functionName);

            functionName = "Transformasi VD58";
            insertDataTrVd58(conn, dto, log, functionName);

            functionName = "Transformasi VD59";
            insertDataTrVd59(conn, dto, log, functionName);
//
//            functionName = "Transformasi VD510A";
//            insertDataTrVd510A(conn, dto, log, functionName);
//
//            functionName = "Transformasi VD510B";
//            insertDataTrVd510B(conn, dto, log, functionName);
//
//            functionName = "Transformasi VD510C";
//            insertDataTrVd510C(conn, dto, log, functionName);
//
//            functionName = "Transformasi VD510D";
//            insertDataTrVd510D(conn, dto, log, functionName);
//
//            functionName = "Transformasi VD510E";
//            insertDataTrVd510E(conn, dto, log, functionName);
//
//            functionName = "Transformasi VD510F";
//            insertDataTrVd510F(conn, dto, log, functionName);
//
//            functionName = "Transformasi VD510G";
//            insertDataTrVd510G(conn, dto, log, functionName);
//
//            functionName = "Transformasi VD510H";
//            insertDataTrVd510H(conn, dto, log, functionName);
//
//            functionName = "Transformasi VD510I";
//            insertDataTrVd510I(conn, dto, log, functionName);

            functionName = "Transformasi VD51";
            insertDataTrVd51(conn, dto, log, functionName);

            conn.commit();
        } catch (Exception e) {
            try {
                log.error(dto.getUsername(), dto.getFilename(), functionName, e.getMessage());
                System.out.println(e.getMessage());
                conn.rollback();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void updateManagerName(Connection conn, MkbTransformDto dto, LogUtil log, String functionName)
            throws Exception {
        String updateQuery = "UPDATE \"Ms_Pe\" " +
                "SET \"ManagerName\" = ?, \"ModifiedAt\" = now(), \"ModifiedBy\" = ? " +
                "WHERE \"Kode\" = ?";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        PreparedStatement stmt = conn.prepareStatement(updateQuery);
        stmt.setString(1, dto.getManagerName());
        stmt.setString(2, dto.getUserId());
        stmt.setString(3, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void insertDataTrVd51(Connection conn, MkbTransformDto dto, LogUtil log, String functionName)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD51\" " +
                "(\"Id\", \"Saldo\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", " +
                "\"KodeAkun\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);

        for (Vd51Dto temp : dto.getVd51()) {
            stmt.setString(1, temp.getId());
            stmt.setBigDecimal(2, temp.getSaldo());
            stmt.setInt(3, temp.getTanggal());
            stmt.setInt(4, temp.getBulan());
            stmt.setInt(5, temp.getTahun());
            stmt.setString(6, temp.getKodePe());
            stmt.setString(7, temp.getKodeAkun());
            stmt.setTimestamp(8, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(9, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeUpdate();
    }

    private static void insertDataTrVd52(Connection conn, MkbTransformDto dto, LogUtil log, String functionName)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD52\" " +
                "(\"Id\", \"Saldo\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", " +
                "\"KodeAkun\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);

        for (Vd52Dto temp : dto.getVd52()) {
            stmt.setString(1, temp.getId());
            stmt.setBigDecimal(2, temp.getSaldo());
            stmt.setInt(3, temp.getTanggal());
            stmt.setInt(4, temp.getBulan());
            stmt.setInt(5, temp.getTahun());
            stmt.setString(6, temp.getKodePe());
            stmt.setString(7, temp.getKodeAkun());
            stmt.setTimestamp(8, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(9, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeUpdate();
    }

    private static void insertDataTrVd53(Connection conn, MkbTransformDto dto, LogUtil log, String functionName)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD53\" " +
                "(\"Id\", \"NilaiDitambahkan\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", " +
                "\"KodeAkun\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);

        for (Vd53Dto temp : dto.getVd53()) {
            stmt.setString(1, temp.getId());
            stmt.setBigDecimal(2, temp.getNilaiDitambahkan());
            stmt.setInt(3, temp.getTanggal());
            stmt.setInt(4, temp.getBulan());
            stmt.setInt(5, temp.getTahun());
            stmt.setString(6, temp.getKodePe());
            stmt.setString(7, temp.getKodeAkun());
            stmt.setTimestamp(8, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(9, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeUpdate();
    }

    private static void insertDataTrVd54(Connection conn, MkbTransformDto dto, LogUtil log, String functionName)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD54\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"KodeAkun\", \"KodeJenisReksadana\", \"NamaJenisReksadana\", " +
                "\"IsAfiliasi\", \"NilaiAktivaBersihUnit\", \"NilaiAktivaBersihReksadana\", " +
                "\"BatasanMkbd\", \"KelebihanMkbd\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);

        for (Vd54Dto temp : dto.getVd54()) {
            stmt.setString(1, temp.getId());
            stmt.setInt(2, temp.getTahun());
            stmt.setInt(3, temp.getBulan());
            stmt.setInt(4, temp.getTanggal());
            stmt.setString(5, temp.getKodePe());
            stmt.setString(6, temp.getKodeAkun());
            stmt.setString(7, temp.getKodeJenisReksadana());
            stmt.setString(8, temp.getNamaJenisReksadana());
            stmt.setBoolean(9, temp.isAfiliasi());
            stmt.setBigDecimal(10, temp.getNilaiAktivaBersihUnit());
            stmt.setBigDecimal(11, temp.getNilaiAktivaBersihReksadana());
            stmt.setBigDecimal(12, temp.getBatasanMkbd());
            stmt.setBigDecimal(13, temp.getKelebihanMkbd());
            stmt.setTimestamp(14, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(15, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeUpdate();
    }

    private static void insertDataTrVd55(Connection conn, MkbTransformDto dto, LogUtil log, String functionName)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD55\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"KodeAkun\", \"KodeEfek\", \"NilaiEfek\", \"KodeEfekLN\", \"NilaiEfekLN\", " +
                "\"NilaiEfekTutupLN\", \"NilaiHaircutTutupLN\", \"NilaiHaircutLN\", " +
                "\"JmlPengembalianHaircut\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);

        for (Vd55Dto temp : dto.getVd55()) {
            stmt.setString(1, temp.getId());
            stmt.setInt(2, temp.getTahun());
            stmt.setInt(3, temp.getBulan());
            stmt.setInt(4, temp.getTanggal());
            stmt.setString(5, temp.getKodePe());
            stmt.setString(6, temp.getKodeAkun());
            stmt.setString(7, temp.getKodeEfek());
            stmt.setBigDecimal(8, temp.getNilaiEfek());
            stmt.setString(9, temp.getKodeEfekLn());
            stmt.setBigDecimal(10, temp.getNilaiEfekLn());
            stmt.setBigDecimal(11, temp.getNilaiEfekTutupLn());
            stmt.setBigDecimal(12, temp.getNilaiHaircutTutupLn());
            stmt.setBigDecimal(13, temp.getNilaiHaircutLn());
            stmt.setBigDecimal(14, temp.getJmlPengembalianHaircut());
            stmt.setTimestamp(15, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(16, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeUpdate();
    }

    private static void insertDataTrVd56A(Connection conn, MkbTransformDto dto, LogUtil log, String functionName)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD56A\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"Saldo\", \"Terafiliasi\", \"TidakTerafiliasi\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);

        for (Vd56aDto temp : dto.getVd56a()) {
            stmt.setString(1, temp.getId());
            stmt.setInt(2, temp.getTahun());
            stmt.setInt(3, temp.getBulan());
            stmt.setInt(4, temp.getTanggal());
            stmt.setString(5, temp.getKodePe());
            stmt.setBigDecimal(6, temp.getSaldo());
            stmt.setBigDecimal(7, temp.getTerafiliasi());
            stmt.setBigDecimal(8, temp.getTidakTerafiliasi());
            stmt.setString(9, temp.getKodeAkun());
            stmt.setTimestamp(10, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(11, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeUpdate();
    }

    private static void insertDataTrVd56B(Connection conn, MkbTransformDto dto, LogUtil log, String functionName)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD56B\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"Saldo\", \"Dimiliki\", \"Dipisahkan\", \"TidakDipisahkan\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);

        for (Vd56bDto temp : dto.getVd56b()) {
            stmt.setString(1, temp.getId());
            stmt.setInt(2, temp.getTahun());
            stmt.setInt(3, temp.getBulan());
            stmt.setInt(4, temp.getTanggal());
            stmt.setString(5, temp.getKodePe());
            stmt.setBigDecimal(6, temp.getSaldo());
            stmt.setBigDecimal(7, temp.getDimiliki());
            stmt.setBigDecimal(8, temp.getDipisahkan());
            stmt.setBigDecimal(9, temp.getTidakDipisahkan());
            stmt.setString(10, temp.getKodeAkun());
            stmt.setTimestamp(11, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(12, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeUpdate();
    }

    private static void insertDataTrVd56C(Connection conn, MkbTransformDto dto, LogUtil log, String functionName)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD56C\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"KodeAkun\", \"KodeBank\", \"Saldo\", \"IsSendiriNasabah\", \"NoRekening\",  " +
                "\"KodeCurrency\", \"SaldoRupiah\", \"Penjelasan\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);

        for (Vd56cDto temp : dto.getVd56c()) {
            stmt.setString(1, temp.getId());
            stmt.setInt(2, temp.getTahun());
            stmt.setInt(3, temp.getBulan());
            stmt.setInt(4, temp.getTanggal());
            stmt.setString(5, temp.getKodePe());
            stmt.setString(6, temp.getKodeAkun());
            stmt.setString(7, temp.getKodeBank());
            stmt.setBigDecimal(8, temp.getSaldo());
            stmt.setBoolean(9, temp.getIsSendiriNasabah() != null && temp.getIsSendiriNasabah());
            stmt.setString(10, temp.getNoRekening());
            stmt.setString(11, temp.getKodeCurrency());
            stmt.setBigDecimal(12, temp.getSaldoRupiah());
            stmt.setString(13, temp.getPenjelasan());
            stmt.setTimestamp(14, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(15, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeUpdate();
    }

    private static void insertDataTrVd57A(Connection conn, MkbTransformDto dto, LogUtil log, String functionName)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD57A\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"Saldo\", \"Terafiliasi\", \"TidakTerafiliasi\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);

        for (Vd57aDto temp : dto.getVd57a()) {
            stmt.setString(1, temp.getId());
            stmt.setInt(2, temp.getTahun());
            stmt.setInt(3, temp.getBulan());
            stmt.setInt(4, temp.getTanggal());
            stmt.setString(5, temp.getKodePe());
            stmt.setBigDecimal(6, temp.getSaldo());
            stmt.setBigDecimal(7, temp.getTerafiliasi());
            stmt.setBigDecimal(8, temp.getTidakTerafiliasi());
            stmt.setString(9, temp.getKodeAkun());
            stmt.setTimestamp(10, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(11, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeUpdate();
    }

    private static void insertDataTrVd57B(Connection conn, MkbTransformDto dto, LogUtil log, String functionName)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD57B\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"Saldo\", \"Dimiliki\", \"Dipisahkan\", \"TidakDipisahkan\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);

        for (Vd57bDto temp : dto.getVd57b()) {
            stmt.setString(1, temp.getId());
            stmt.setInt(2, temp.getTahun());
            stmt.setInt(3, temp.getBulan());
            stmt.setInt(4, temp.getTanggal());
            stmt.setString(5, temp.getKodePe());
            stmt.setBigDecimal(6, temp.getSaldo());
            stmt.setBigDecimal(7, temp.getDimiliki());
            stmt.setBigDecimal(8, temp.getDipisahkan());
            stmt.setBigDecimal(9, temp.getTidakDipisahkan());
            stmt.setString(10, temp.getKodeAkun());
            stmt.setTimestamp(11, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(12, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeUpdate();
    }

    private static void insertDataTrVd57C(Connection conn, MkbTransformDto dto, LogUtil log, String functionName)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD57C\"\n" +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"Saldo\", \"LmHrKerja\", \"Dimiliki\", \"Dipisahkan\", \"KodeAkun\", " +
                "\"CreatedAt\", \"CreatedBy\", \"Penjelasan\")" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);

        for (Vd57cDto temp : dto.getVd57c()) {
            stmt.setString(1, temp.getId());
            stmt.setInt(2, temp.getTahun());
            stmt.setInt(3, temp.getBulan());
            stmt.setInt(4, temp.getTanggal());
            stmt.setString(5, temp.getKodePe());
            stmt.setBigDecimal(6, temp.getSaldo());
            stmt.setBigDecimal(7, temp.getLmHrKerja());
            stmt.setBigDecimal(8, temp.getDimiliki());
            stmt.setBigDecimal(9, temp.getDipisahkan());
            stmt.setString(10, temp.getKodeAkun());
            stmt.setTimestamp(11, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(12, temp.getCreatedBy());
            stmt.setString(13, temp.getPenjelasan());
            stmt.addBatch();
        }
        stmt.executeUpdate();
    }

    private static void insertDataTrVd58(Connection conn, MkbTransformDto dto, LogUtil log, String functionName)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD58\" " +
                "(\"Id\", \"Nilai\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", " +
                "\"KodeAkun\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);

        for (Vd58Dto temp : dto.getVd58()) {
            stmt.setString(1, temp.getId());
            stmt.setBigDecimal(2, temp.getNilai());
            stmt.setInt(3, temp.getTanggal());
            stmt.setInt(4, temp.getBulan());
            stmt.setInt(5, temp.getTahun());
            stmt.setString(6, temp.getKodePe());
            stmt.setString(7, temp.getKodeAkun());
            stmt.setTimestamp(8, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(9, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeUpdate();
    }

    private static void insertDataTrVd59(Connection conn, MkbTransformDto dto, LogUtil log, String functionName)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD59\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"KodeAkun\", \"Jumlah\", \"Total\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);

        for (Vd59Dto temp : dto.getVd59()) {
            stmt.setString(1, temp.getId());
            stmt.setInt(2, temp.getTahun());
            stmt.setInt(3, temp.getBulan());
            stmt.setInt(4, temp.getTanggal());
            stmt.setString(5, temp.getKodePe());
            stmt.setString(6, temp.getKodeAkun());
            stmt.setBigDecimal(7, temp.getJumlah());
            stmt.setBigDecimal(8, temp.getTotal());
            stmt.setTimestamp(9, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(10, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeUpdate();
    }
}
