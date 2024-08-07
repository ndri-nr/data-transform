package com.kpei.mkbd.datatransform.service;

import com.kpei.mkbd.datatransform.dto.*;
import com.kpei.mkbd.datatransform.model.FormulaAkun;
import com.kpei.mkbd.datatransform.util.LogUtil;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProcessService {
    private static String functionName = "";

    public static String processDataVD(Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) {
        try {
            conn.setAutoCommit(false);

            cleansingDataIfExist(conn, dto, logger);

            updateManagerName(conn, dto, log, logger);
            processInsertCurrent(conn, dto, log, logger);
            processInsertCurrentKPEI(conn, dto, log, logger);
            processKPEI(conn, dto, log, logger);
            processPerhitungan(conn, dto, log, logger);
            processHistorical(conn, dto, log, logger);
            processHistoricalKPEI(conn, dto, log, logger);

            log.process(dto.getUsername(), dto.getFilename(), "Set Log Pengiriman Status to ACTIVE");
            logger.info("Process " + "Set Log Pengiriman Status to ACTIVE");
            setLogPengirimanActive(conn, dto);

            conn.commit();

            return 1 + "|SUCCESS";
        } catch (Exception e) {
            try {
                log.error(dto.getUsername(), dto.getFilename(), functionName, e.getMessage());
                logger.error("Rollback because : " + e.getMessage());
                conn.rollback();
                return 0 + "|" + e.getMessage();
            } catch (Exception ex) {
                return 0 + "|" + ex.getMessage();
            }
        }
    }

    private static void updateManagerName(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        functionName = "Update Manager Name";
        String updateQuery = "UPDATE \"Ms_Pe\" " +
                "SET \"ManagerName\" = ?, \"ModifiedAt\" = now(), \"ModifiedBy\" = ? " +
                "WHERE \"Kode\" = ?";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + updateQuery);
        PreparedStatement stmt = conn.prepareStatement(updateQuery);
        stmt.setQueryTimeout(30);
        stmt.setString(1, dto.getManagerName());
        stmt.setString(2, dto.getUserId());
        stmt.setString(3, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processInsertCurrent(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        log.process(dto.getUsername(), dto.getFilename(), "Insert Current Process Started");
        logger.info("=========== Insert Current Process Started ===========");

        functionName = "Inserting data Tr_VD51";
        insertDataTrVd51(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD52";
        insertDataTrVd52(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD53";
        insertDataTrVd53(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD54";
        insertDataTrVd54(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD55";
        insertDataTrVd55(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD56A";
        insertDataTrVd56A(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD56B";
        insertDataTrVd56B(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD56C";
        insertDataTrVd56C(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD57A";
        insertDataTrVd57A(conn, dto, log, logger);

        functionName = "Inserting data Tr_D57B";
        insertDataTrVd57B(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD57C";
        insertDataTrVd57C(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD58";
        insertDataTrVd58(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD59";
        insertDataTrVd59(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510A";
        insertDataTrVd510A(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510B";
        insertDataTrVd510B(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510C";
        insertDataTrVd510C(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510D";
        insertDataTrVd510D(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510E";
        insertDataTrVd510E(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510F";
        insertDataTrVd510F(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510G";
        insertDataTrVd510G(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510H";
        insertDataTrVd510H(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510I";
        insertDataTrVd510I(conn, dto, log, logger);

        log.process(dto.getUsername(), dto.getFilename(), "Insert Current Process Finished");
        logger.info("=========== Insert Current Process Finished ===========");
    }

    private static void insertDataTrVd51(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD51\" " +
                "(\"Id\", \"Saldo\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", " +
                "\"KodeAkun\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd52(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD52\" " +
                "(\"Id\", \"Saldo\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", " +
                "\"KodeAkun\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd53(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD53\" " +
                "(\"Id\", \"NilaiDitambahkan\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", " +
                "\"KodeAkun\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd54(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD54\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"KodeAkun\", \"KodeJenisReksadana\", \"NamaJenisReksadana\", " +
                "\"IsAfiliasi\", \"NilaiAktivaBersihUnit\", \"NilaiAktivaBersihReksadana\", " +
                "\"BatasanMkbd\", \"KelebihanMkbd\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd55(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD55\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"KodeAkun\", \"KodeEfek\", \"NilaiEfek\", \"KodeEfekLN\", \"NilaiEfekLN\", " +
                "\"NilaiEfekTutupLN\", \"NilaiHaircutTutupLN\", \"NilaiHaircutLN\", " +
                "\"JmlPengembalianHaircut\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd56A(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD56A\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"Saldo\", \"Terafiliasi\", \"TidakTerafiliasi\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd56B(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD56B\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"Saldo\", \"Dimiliki\", \"Dipisahkan\", \"TidakDipisahkan\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd56C(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD56C\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"KodeAkun\", \"KodeBank\", \"Saldo\", \"IsSendiriNasabah\", \"NoRekening\",  " +
                "\"KodeCurrency\", \"SaldoRupiah\", \"Penjelasan\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd57A(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD57A\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"Saldo\", \"Terafiliasi\", \"TidakTerafiliasi\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd57B(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD57B\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"Saldo\", \"Dimiliki\", \"Dipisahkan\", \"TidakDipisahkan\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd57C(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD57C\"\n" +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"Saldo\", \"LmHrKerja\", \"Dimiliki\", \"Dipisahkan\", \"KodeAkun\", " +
                "\"CreatedAt\", \"CreatedBy\", \"Penjelasan\")" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd58(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD58\" " +
                "(\"Id\", \"Nilai\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", " +
                "\"KodeAkun\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd59(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD59\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"KodeAkun\", \"Jumlah\", \"Total\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd510A(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510A\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", " +
                "\"Bulan\", \"Tanggal\", \"Pembeli\", \"TanggalPembelian\", \"TanggalPenjualan\", " +
                "\"NilaiPembelian\", \"NilaiPenjualan\", \"KodeEfekKolateral\", \"JumlahJaminan\", \"NilaiPasarWajar\", " +
                "\"NilaiRankingLiabilitas\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510aDto temp : dto.getVd510a()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setString(4, temp.getKodeEfek());
            stmt.setInt(5, temp.getTahun());
            stmt.setInt(6, temp.getBulan());
            stmt.setInt(7, temp.getTanggal());
            stmt.setString(8, temp.getPembeli());
            stmt.setDate(9, temp.getTanggalPembelian());
            stmt.setDate(10, temp.getTanggalPenjualan());
            stmt.setBigDecimal(11, temp.getNilaiPembelian());
            stmt.setBigDecimal(12, temp.getNilaiPenjualan());
            stmt.setString(13, temp.getKodeEfekKolateral());
            stmt.setBigDecimal(14, temp.getJumlahJaminan());
            stmt.setBigDecimal(15, temp.getNilaiPasarWajar());
            stmt.setBigDecimal(16, temp.getNilaiRankingLiabilitas());
            stmt.setTimestamp(17, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(18, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510B(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510B\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", " +
                "\"Bulan\", \"Tanggal\", \"Penjual\", \"TanggalPembelian\", \"TanggalPenjualan\", " +
                "\"NilaiPembelian\", \"NilaiPenjualan\", \"KodeEfekKolateral\", \"JumlahJaminan\", " +
                "\"NilaiPasarWajar\", " +
                "\"NilaiRankingLiabilitas\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510bDto temp : dto.getVd510b()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setString(4, temp.getKodeEfek());
            stmt.setInt(5, temp.getTahun());
            stmt.setInt(6, temp.getBulan());
            stmt.setInt(7, temp.getTanggal());
            stmt.setString(8, temp.getPenjual());
            stmt.setDate(9, temp.getTanggalPembelian());
            stmt.setDate(10, temp.getTanggalPenjualan());
            stmt.setBigDecimal(11, temp.getNilaiPembelian());
            stmt.setBigDecimal(12, temp.getNilaiPenjualan());
            stmt.setString(13, temp.getKodeEfekKolateral());
            stmt.setBigDecimal(14, temp.getJumlahJaminan());
            stmt.setBigDecimal(15, temp.getNilaiPasarWajar());
            stmt.setBigDecimal(16, temp.getNilaiRankingLiabilitas());
            stmt.setTimestamp(17, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(18, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510C(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510C\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", " +
                "\"Bulan\", \"Tanggal\", \"Terafiliasi\", \"GrupEmiten\", \"PersentaseNilaiPasar\", " +
                "\"LembarNominal\", \"HargaPerolehan\", \"HargaPasarWajar\", \"NilaiPasarWajar\", " +
                "\"NilaiRankingLiabilitas\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510cDto temp : dto.getVd510c()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setString(4, temp.getKodeEfek());
            stmt.setInt(5, temp.getTahun());
            stmt.setInt(6, temp.getBulan());
            stmt.setInt(7, temp.getTanggal());
            stmt.setString(8, temp.getTerafiliasi());
            stmt.setString(9, temp.getGrupEmiten());
            stmt.setString(10, temp.getPersentaseNilaiPasar());
            stmt.setBigDecimal(11, temp.getLembarNominal());
            stmt.setBigDecimal(12, temp.getHargaPerolehan());
            stmt.setBigDecimal(13, temp.getHargaPasarWajar());
            stmt.setBigDecimal(14, temp.getNilaiPasarWajar());
            stmt.setBigDecimal(15, temp.getNilaiRankingLiabilitas());
            stmt.setTimestamp(16, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(17, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510D(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510D\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"NamaNasabah\", \"Tahun\", " +
                "\"Bulan\", \"Tanggal\", \"MarginSelling\", \"NilaiPembiayaan\", \"NilaiJaminan\", " +
                "\"RasioPembiayaan\", \"NilaiRankingLiabilitiesNasabah\", \"NilaiRankingLiabilitiesRasio\", " +
                "\"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510dDto temp : dto.getVd510d()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setString(4, temp.getNamaNasabah());
            stmt.setInt(5, temp.getTahun());
            stmt.setInt(6, temp.getBulan());
            stmt.setInt(7, temp.getTanggal());
            stmt.setString(8, temp.getMarginSelling());
            stmt.setBigDecimal(9, temp.getNilaiPembiayaan());
            stmt.setBigDecimal(10, temp.getNilaiJaminan());
            stmt.setBigDecimal(11, temp.getRasioPembiayaan());
            stmt.setBigDecimal(12, temp.getNilaiRankingLiabilitiesNasabah());
            stmt.setBigDecimal(13, temp.getNilaiRankingLiabilitiesRasio());
            stmt.setTimestamp(14, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(15, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510E(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510E\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", " +
                "\"Bulan\", \"Tanggal\", \"Volume\", \"Harga\", \"NilaiPasarWajar\", " +
                "\"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510eDto temp : dto.getVd510e()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setString(4, temp.getKodeEfek());
            stmt.setInt(5, temp.getTahun());
            stmt.setInt(6, temp.getBulan());
            stmt.setInt(7, temp.getTanggal());
            stmt.setBigDecimal(8, temp.getVolume());
            stmt.setBigDecimal(9, temp.getHarga());
            stmt.setBigDecimal(10, temp.getNilaiPasarWajar());
            stmt.setTimestamp(11, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(12, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510F(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510F\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", " +
                "\"Tanggal\", \"TanggalKontrak\", \"JenisPenjaminan\", \"PihakDijamin\", \"StatuPenjaminan\", " +
                "\"NilaiKomitmenPenjaminan\", \"HaircutAtasEfek\", \"NilaiBelumTerserap\", \"NilaiBankGaransi\", " +
                "\"NilaiRankingLiabilitas\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510fDto temp : dto.getVd510f()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setInt(4, temp.getTahun());
            stmt.setInt(5, temp.getBulan());
            stmt.setInt(6, temp.getTanggal());
            stmt.setDate(7, temp.getTanggalKontrak());
            stmt.setString(8, temp.getJenisPenjaminan());
            stmt.setString(9, temp.getPihakDijamin());
            stmt.setString(10, temp.getStatuPenjaminan());
            stmt.setBigDecimal(11, temp.getNilaiKomitmenPenjaminan());
            stmt.setBigDecimal(12, temp.getHaircutAtasEfek());
            stmt.setBigDecimal(13, temp.getNilaiBelumTerserap());
            stmt.setBigDecimal(14, temp.getNilaiBankGaransi());
            stmt.setBigDecimal(15, temp.getNilaiRankingLiabilitas());
            stmt.setTimestamp(16, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(17, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510G(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510G\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", " +
                "\"Tanggal\", \"TanggalKontrak\", \"Terafiliasi\", \"PihakDijamin\", \"RincianPenjaminan\", " +
                "\"JangkaWaktuPnejaminan\", \"TanggalBerakhir\", \"NilaiPenjaminan\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510gDto temp : dto.getVd510g()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setInt(4, temp.getTahun());
            stmt.setInt(5, temp.getBulan());
            stmt.setInt(6, temp.getTanggal());
            stmt.setDate(7, temp.getTanggalKontrak());
            stmt.setString(8, temp.getTerafiliasi());
            stmt.setString(9, temp.getPihakDijamin());
            stmt.setString(10, temp.getRincianPenjaminan());
            stmt.setString(11, temp.getJangkaWaktuPnejaminan());
            stmt.setDate(12, temp.getTanggalBerakhir());
            stmt.setBigDecimal(13, temp.getNilaiPenjaminan());
            stmt.setBigDecimal(14, temp.getNilaiRankingLiabilitas());
            stmt.setTimestamp(15, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(16, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510H(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510H\"\n" +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", " +
                "\"Tanggal\", \"TanggalKomitmen\", \"RincianBelanja\", \"TanggalRealisasi\", \"RincianPenjaminan\", " +
                "\"KomitmenTerealisasi\", \"KomitmenBelumTerealisasi\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510hDto temp : dto.getVd510h()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setInt(4, temp.getTahun());
            stmt.setInt(5, temp.getBulan());
            stmt.setInt(6, temp.getTanggal());
            stmt.setDate(7, temp.getTanggalKomitmen());
            stmt.setString(8, temp.getRincianBelanja());
            stmt.setDate(9, temp.getTanggalRealisasi());
            stmt.setString(10, temp.getRincianPenjaminan());
            stmt.setBigDecimal(11, temp.getKomitmenTerealisasi());
            stmt.setBigDecimal(12, temp.getKomitmenBelumTerealisasi());
            stmt.setBigDecimal(13, temp.getNilaiRankingLiabilitas());
            stmt.setTimestamp(14, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(15, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510I(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510I\"\n" +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", " +
                "\"Tanggal\", \"TanggalTransaksi\", \"JenisTransaksi\", \"JenisMataUang\", \"NilaiTransaksi\", " +
                "\"UntungRugiBelumTerealisasi\", \"NilaiRankingLiabilitas\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510iDto temp : dto.getVd510i()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setInt(4, temp.getTahun());
            stmt.setInt(5, temp.getBulan());
            stmt.setInt(6, temp.getTanggal());
            stmt.setDate(7, temp.getTanggalTransaksi());
            stmt.setString(8, temp.getJenisTransaksi());
            stmt.setString(9, temp.getJenisMataUang());
            stmt.setBigDecimal(10, temp.getNilaiTransaksi());
            stmt.setBigDecimal(11, temp.getUntungRugiBelumTerealisasi());
            stmt.setBigDecimal(12, temp.getNilaiRankingLiabilitas());
            stmt.setTimestamp(13, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(14, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void processInsertCurrentKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        log.process(dto.getUsername(), dto.getFilename(), "Insert Current KPEI Process Started");
        logger.info("========= Insert Current KPEI Process Started =========");

        functionName = "Inserting data Tr_VD51_KPEI";
        insertDataTrVd51KPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD52_KPEI";
        insertDataTrVd52KPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD53_KPEI";
        insertDataTrVd53KPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD54_KPEI";
        insertDataTrVd54KPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD55_KPEI";
        insertDataTrVd55KPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD56A_KPEI";
        insertDataTrVd56AKPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD56B_KPEI";
        insertDataTrVd56BKPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD56C_KPEI";
        insertDataTrVd56CKPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD57A_KPEI";
        insertDataTrVd57AKPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_D57B_KPEI";
        insertDataTrVd57BKPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD57C_KPEI";
        insertDataTrVd57CKPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD58_KPEI";
        insertDataTrVd58KPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD59_KPEI";
        insertDataTrVd59KPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510A_KPEI";
        insertDataTrVd510AKPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510B_KPEI";
        insertDataTrVd510BKPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510C_KPEI";
        insertDataTrVd510CKPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510D_KPEI";
        insertDataTrVd510DKPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510E_KPEI";
        insertDataTrVd510EKPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510F_KPEI";
        insertDataTrVd510FKPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510G_KPEI";
        insertDataTrVd510GKPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510H_KPEI";
        insertDataTrVd510HKPEI(conn, dto, log, logger);

        functionName = "Inserting data Tr_VD510I_KPEI";
        insertDataTrVd510IKPEI(conn, dto, log, logger);

        log.process(dto.getUsername(), dto.getFilename(), "Insert Current KPEI Process Finished");
        logger.info("========= Insert Current KPEI Process Finished =========");
    }

    private static void insertDataTrVd51KPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD51_KPEI\" " +
                "(\"Id\", \"Saldo\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", " +
                "\"KodeAkun\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd52KPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD52_KPEI\" " +
                "(\"Id\", \"Saldo\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", " +
                "\"KodeAkun\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd53KPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD53_KPEI\" " +
                "(\"Id\", \"NilaiDitambahkan\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", " +
                "\"KodeAkun\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd54KPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD54_KPEI\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"KodeAkun\", \"KodeJenisReksadana\", \"NamaJenisReksadana\", " +
                "\"IsAfiliasi\", \"NilaiAktivaBersihUnit\", \"NilaiAktivaBersihReksadana\", " +
                "\"BatasanMkbd\", \"KelebihanMkbd\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd55KPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD55_KPEI\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"KodeAkun\", \"KodeEfek\", \"NilaiEfek\", \"KodeEfekLN\", \"NilaiEfekLN\", " +
                "\"NilaiEfekTutupLN\", \"NilaiHaircutTutupLN\", \"NilaiHaircutLN\", " +
                "\"JmlPengembalianHaircut\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd56AKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD56A_KPEI\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"Saldo\", \"Terafiliasi\", \"TidakTerafiliasi\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd56BKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD56B_KPEI\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"Saldo\", \"Dimiliki\", \"Dipisahkan\", \"TidakDipisahkan\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd56CKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD56C_KPEI\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"KodeAkun\", \"KodeBank\", \"Saldo\", \"IsSendiriNasabah\", \"NoRekening\",  " +
                "\"KodeCurrency\", \"SaldoRupiah\", \"Penjelasan\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd57AKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD57A_KPEI\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"Saldo\", \"Terafiliasi\", \"TidakTerafiliasi\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd57BKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD57B_KPEI\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"Saldo\", \"Dimiliki\", \"Dipisahkan\", \"TidakDipisahkan\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd57CKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD57C_KPEI\"\n" +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"Saldo\", \"LmHrKerja\", \"Dimiliki\", \"Dipisahkan\", \"KodeAkun\", " +
                "\"CreatedAt\", \"CreatedBy\", \"Penjelasan\")" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd58KPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD58_KPEI\" " +
                "(\"Id\", \"Nilai\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", " +
                "\"KodeAkun\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd59KPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD59_KPEI\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", " +
                "\"KodeAkun\", \"Jumlah\", \"Total\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

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
        stmt.executeBatch();
    }

    private static void insertDataTrVd510AKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510A_KPEI\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", " +
                "\"Bulan\", \"Tanggal\", \"Pembeli\", \"TanggalPembelian\", \"TanggalPenjualan\", " +
                "\"NilaiPembelian\", \"NilaiPenjualan\", \"KodeEfekKolateral\", \"JumlahJaminan\", \"NilaiPasarWajar\", " +
                "\"NilaiRankingLiabilitas\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510aDto temp : dto.getVd510a()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setString(4, temp.getKodeEfek());
            stmt.setInt(5, temp.getTahun());
            stmt.setInt(6, temp.getBulan());
            stmt.setInt(7, temp.getTanggal());
            stmt.setString(8, temp.getPembeli());
            stmt.setDate(9, temp.getTanggalPembelian());
            stmt.setDate(10, temp.getTanggalPenjualan());
            stmt.setBigDecimal(11, temp.getNilaiPembelian());
            stmt.setBigDecimal(12, temp.getNilaiPenjualan());
            stmt.setString(13, temp.getKodeEfekKolateral());
            stmt.setBigDecimal(14, temp.getJumlahJaminan());
            stmt.setBigDecimal(15, temp.getNilaiPasarWajar());
            stmt.setBigDecimal(16, temp.getNilaiRankingLiabilitas());
            stmt.setTimestamp(17, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(18, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510BKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510B_KPEI\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", " +
                "\"Bulan\", \"Tanggal\", \"Penjual\", \"TanggalPembelian\", \"TanggalPenjualan\", " +
                "\"NilaiPembelian\", \"NilaiPenjualan\", \"KodeEfekKolateral\", \"JumlahJaminan\", \"NilaiPasarWajar\", " +
                "\"NilaiRankingLiabilitas\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510bDto temp : dto.getVd510b()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setString(4, temp.getKodeEfek());
            stmt.setInt(5, temp.getTahun());
            stmt.setInt(6, temp.getBulan());
            stmt.setInt(7, temp.getTanggal());
            stmt.setString(8, temp.getPenjual());
            stmt.setDate(9, temp.getTanggalPembelian());
            stmt.setDate(10, temp.getTanggalPenjualan());
            stmt.setBigDecimal(11, temp.getNilaiPembelian());
            stmt.setBigDecimal(12, temp.getNilaiPenjualan());
            stmt.setString(13, temp.getKodeEfekKolateral());
            stmt.setBigDecimal(14, temp.getJumlahJaminan());
            stmt.setBigDecimal(15, temp.getNilaiPasarWajar());
            stmt.setBigDecimal(16, temp.getNilaiRankingLiabilitas());
            stmt.setTimestamp(17, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(18, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510CKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510C_KPEI\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", " +
                "\"Bulan\", \"Tanggal\", \"Terafiliasi\", \"GrupEmiten\", \"PersentaseNilaiPasar\", " +
                "\"LembarNominal\", \"HargaPerolehan\", \"HargaPasarWajar\", \"NilaiPasarWajar\", " +
                "\"NilaiRankingLiabilitas\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510cDto temp : dto.getVd510c()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setString(4, temp.getKodeEfek());
            stmt.setInt(5, temp.getTahun());
            stmt.setInt(6, temp.getBulan());
            stmt.setInt(7, temp.getTanggal());
            stmt.setString(8, temp.getTerafiliasi());
            stmt.setString(9, temp.getGrupEmiten());
            stmt.setString(10, temp.getPersentaseNilaiPasar());
            stmt.setBigDecimal(11, temp.getLembarNominal());
            stmt.setBigDecimal(12, temp.getHargaPerolehan());
            stmt.setBigDecimal(13, temp.getHargaPasarWajar());
            stmt.setBigDecimal(14, temp.getNilaiPasarWajar());
            stmt.setBigDecimal(15, temp.getNilaiRankingLiabilitas());
            stmt.setTimestamp(16, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(17, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510DKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510D_KPEI\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"NamaNasabah\", \"Tahun\", " +
                "\"Bulan\", \"Tanggal\", \"MarginSelling\", \"NilaiPembiayaan\", \"NilaiJaminan\", " +
                "\"RasioPembiayaan\", \"NilaiRankingLiabilitiesNasabah\", \"NilaiRankingLiabilitiesRasio\", " +
                "\"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510dDto temp : dto.getVd510d()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setString(4, temp.getNamaNasabah());
            stmt.setInt(5, temp.getTahun());
            stmt.setInt(6, temp.getBulan());
            stmt.setInt(7, temp.getTanggal());
            stmt.setString(8, temp.getMarginSelling());
            stmt.setBigDecimal(9, temp.getNilaiPembiayaan());
            stmt.setBigDecimal(10, temp.getNilaiJaminan());
            stmt.setBigDecimal(11, temp.getRasioPembiayaan());
            stmt.setBigDecimal(12, temp.getNilaiRankingLiabilitiesNasabah());
            stmt.setBigDecimal(13, temp.getNilaiRankingLiabilitiesRasio());
            stmt.setTimestamp(14, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(15, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510EKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510E_KPEI\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", " +
                "\"Bulan\", \"Tanggal\", \"Volume\", \"Harga\", \"NilaiPasarWajar\", " +
                "\"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510eDto temp : dto.getVd510e()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setString(4, temp.getKodeEfek());
            stmt.setInt(5, temp.getTahun());
            stmt.setInt(6, temp.getBulan());
            stmt.setInt(7, temp.getTanggal());
            stmt.setBigDecimal(8, temp.getVolume());
            stmt.setBigDecimal(9, temp.getHarga());
            stmt.setBigDecimal(10, temp.getNilaiPasarWajar());
            stmt.setTimestamp(11, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(12, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510FKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510F_KPEI\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", " +
                "\"Tanggal\", \"TanggalKontrak\", \"JenisPenjaminan\", \"PihakDijamin\", \"StatuPenjaminan\", " +
                "\"NilaiKomitmenPenjaminan\", \"HaircutAtasEfek\", \"NilaiBelumTerserap\", \"NilaiBankGaransi\", " +
                "\"NilaiRankingLiabilitas\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510fDto temp : dto.getVd510f()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setInt(4, temp.getTahun());
            stmt.setInt(5, temp.getBulan());
            stmt.setInt(6, temp.getTanggal());
            stmt.setDate(7, temp.getTanggalKontrak());
            stmt.setString(8, temp.getJenisPenjaminan());
            stmt.setString(9, temp.getPihakDijamin());
            stmt.setString(10, temp.getStatuPenjaminan());
            stmt.setBigDecimal(11, temp.getNilaiKomitmenPenjaminan());
            stmt.setBigDecimal(12, temp.getHaircutAtasEfek());
            stmt.setBigDecimal(13, temp.getNilaiBelumTerserap());
            stmt.setBigDecimal(14, temp.getNilaiBankGaransi());
            stmt.setBigDecimal(15, temp.getNilaiRankingLiabilitas());
            stmt.setTimestamp(16, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(17, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510GKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510G_KPEI\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", " +
                "\"Tanggal\", \"TanggalKontrak\", \"Terafiliasi\", \"PihakDijamin\", \"RincianPenjaminan\", " +
                "\"JangkaWaktuPnejaminan\", \"TanggalBerakhir\", \"NilaiPenjaminan\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510gDto temp : dto.getVd510g()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setInt(4, temp.getTahun());
            stmt.setInt(5, temp.getBulan());
            stmt.setInt(6, temp.getTanggal());
            stmt.setDate(7, temp.getTanggalKontrak());
            stmt.setString(8, temp.getTerafiliasi());
            stmt.setString(9, temp.getPihakDijamin());
            stmt.setString(10, temp.getRincianPenjaminan());
            stmt.setString(11, temp.getJangkaWaktuPnejaminan());
            stmt.setDate(12, temp.getTanggalBerakhir());
            stmt.setBigDecimal(13, temp.getNilaiPenjaminan());
            stmt.setBigDecimal(14, temp.getNilaiRankingLiabilitas());
            stmt.setTimestamp(15, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(16, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510HKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510H_KPEI\"\n" +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", " +
                "\"Tanggal\", \"TanggalKomitmen\", \"RincianBelanja\", \"TanggalRealisasi\", \"RincianPenjaminan\", " +
                "\"KomitmenTerealisasi\", \"KomitmenBelumTerealisasi\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510hDto temp : dto.getVd510h()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setInt(4, temp.getTahun());
            stmt.setInt(5, temp.getBulan());
            stmt.setInt(6, temp.getTanggal());
            stmt.setDate(7, temp.getTanggalKomitmen());
            stmt.setString(8, temp.getRincianBelanja());
            stmt.setDate(9, temp.getTanggalRealisasi());
            stmt.setString(10, temp.getRincianPenjaminan());
            stmt.setBigDecimal(11, temp.getKomitmenTerealisasi());
            stmt.setBigDecimal(12, temp.getKomitmenBelumTerealisasi());
            stmt.setBigDecimal(13, temp.getNilaiRankingLiabilitas());
            stmt.setTimestamp(14, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(15, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void insertDataTrVd510IKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        String insertQuery = "INSERT INTO public.\"Tr_VD510I_KPEI\"\n" +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", " +
                "\"Tanggal\", \"TanggalTransaksi\", \"JenisTransaksi\", \"JenisMataUang\", \"NilaiTransaksi\", " +
                "\"UntungRugiBelumTerealisasi\", \"NilaiRankingLiabilitas\", \"CreatedAt\", \"CreatedBy\") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        for (Vd510iDto temp : dto.getVd510i()) {
            stmt.setString(1, temp.getId());
            stmt.setString(2, temp.getKodeAkun());
            stmt.setString(3, temp.getKodePe());
            stmt.setInt(4, temp.getTahun());
            stmt.setInt(5, temp.getBulan());
            stmt.setInt(6, temp.getTanggal());
            stmt.setDate(7, temp.getTanggalTransaksi());
            stmt.setString(8, temp.getJenisTransaksi());
            stmt.setString(9, temp.getJenisMataUang());
            stmt.setBigDecimal(10, temp.getNilaiTransaksi());
            stmt.setBigDecimal(11, temp.getUntungRugiBelumTerealisasi());
            stmt.setBigDecimal(12, temp.getNilaiRankingLiabilitas());
            stmt.setTimestamp(13, Timestamp.valueOf(temp.getCreatedAt()));
            stmt.setString(14, temp.getCreatedBy());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static void processKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        log.process(dto.getUsername(), dto.getFilename(), "Update KPEI Process Started");
        logger.info("=========== Update KPEI Process Started ===========");

        functionName = "Get Jenis Usaha PPE";
        String jenisUsahaPpe = getJenisUsahaPPE(conn, dto, log, logger);

        functionName = "Get Jenis Usaha MI";
        String jenisUsahaMi = getJenisUsahaMI(conn, dto, log, logger);

        functionName = "Get Nilai PPE";
        BigDecimal nilaiPpe = getNilaiBigDecimalByParameter(conn, dto, log, logger, jenisUsahaPpe);

        functionName = "Get Nilai MI";
        BigDecimal nilaiMi = getNilaiBigDecimalByParameter(conn, dto, log, logger, jenisUsahaMi);

        functionName = "Update Nilai Transaksi Table Tr_VD58_KPEI based on Jenis Usaha";
        updateNilaiTransaksiKpeiByJenisUsaha(
                conn, dto, log, logger, jenisUsahaPpe, jenisUsahaMi, nilaiPpe, nilaiMi);

        functionName = "Get List Formula Akun";
        List<FormulaAkun> formulaAkun = getFormulaAkun(conn, dto, log, logger);

        functionName = "Updated data Tr_VD_KPEI";
        processUpdateByFormula(conn, dto, log, logger, formulaAkun);

        log.process(dto.getUsername(), dto.getFilename(), "Update KPEI Process Finished");
        logger.info("=========== Update KPEI Process finished ===========");
    }

    private static String getJenisUsahaPPE(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws SQLException {
        String result = "";
        String query = "SELECT " +
                "    CASE " +
                "        WHEN \"IsNonAB\"=true AND \"IsPPE\" =false AND \"IsPEE\"=false " +
                "        THEN 'NON AB' " +
                "        WHEN \"IsNonAB\"=false AND \"IsPPE\" =true AND  \"IsPEE\"=false AND \"IsMI\" =false " +
                "        THEN 'PPE' " +
                "        WHEN \"IsNonAB\"=true AND \"IsPPE\" =true AND  \"IsPEE\"=false AND \"IsMI\" =false " +
                "        THEN 'PPE' " +
                "        WHEN \"IsNonAB\"=true AND \"IsPPE\" =true AND  \"IsPEE\"=false AND \"IsMI\" =true " +
                "        THEN 'PPE' " +
                "        WHEN \"IsNonAB\"=false AND \"IsPPE\" =true AND \"IsPEE\"=false AND \"IsMI\" =true " +
                "        THEN 'PPE' " +
                "        WHEN \"IsNonAB\"=false AND \"IsPPE\" =true AND \"IsPEE\"=false AND \"IsMI\" =false " +
                "        THEN 'PPE' " +
                "        WHEN \"IsNonAB\"=false AND \"IsPPE\" =true AND \"IsPEE\"=true AND \"IsMI\" =false " +
                "        THEN 'PPE' " +
                "        WHEN \"IsNonAB\"=true AND \"IsPPE\" =false AND \"IsPEE\"=true AND \"IsMI\" =false " +
                "        THEN 'NONAB PEE' " +
                "        WHEN \"IsNonAB\"=true AND \"IsPPE\" =false AND \"IsPEE\"=true AND \"IsMI\" =true " +
                "        THEN 'NONAB PEE MI' " +
                "        WHEN \"IsNonAB\"=false AND \"IsPPE\" =true AND \"IsPEE\"=true AND \"IsMI\" =true " +
                "        THEN 'PPE' " +
                "        WHEN \"IsNonAB\"=false AND \"IsPPE\" =false AND \"IsPEE\"=true AND \"IsMI\" =false " +
                "        THEN 'PEE' " +
                "        WHEN \"IsNonAB\"=false AND \"IsPPE\" =false AND \"IsPEE\"=false AND \"IsMI\" =false " +
                "             AND \"IsPED\" = true " +
                "        THEN 'PED' " +
                "        ELSE 'NONE' " +
                "    END AS jenis_usaha_pe " +
                "FROM \"Ms_Pe\" " +
                "WHERE \"Kode\"= ?";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + query);
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setQueryTimeout(30);
        stmt.setString(1, dto.getKodePe());
        ResultSet myRs = stmt.executeQuery();

        while (myRs.next()) {
            result = myRs.getString("jenis_usaha_pe");
            break;
        }

        return result;
    }

    private static String getJenisUsahaMI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws SQLException {
        String result = "";
        String query = "SELECT " +
                "    CASE WHEN \"IsMI\" = true " +
                "        THEN 'MI' " +
                "        ELSE 'NONE' " +
                "    END AS jenis_usaha_mi " +
                "FROM \"Ms_Pe\" " +
                "WHERE \"Kode\" = ?";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + query);
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setQueryTimeout(30);
        stmt.setString(1, dto.getKodePe());
        ResultSet myRs = stmt.executeQuery();

        while (myRs.next()) {
            result = myRs.getString("jenis_usaha_mi");
            break;
        }

        return result;
    }

    private static BigDecimal getNilaiBigDecimalByParameter(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger, String jenisUsaha)
            throws SQLException {
        BigDecimal result = null;
        String query = "SELECT CAST(mp.\"Value\" AS FLOAT) as nilai " +
                "FROM \"Ms_Parameter\" mp  WHERE \"Name\" = ?";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + query);
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setQueryTimeout(30);
        stmt.setString(1, jenisUsaha);
        ResultSet myRs = stmt.executeQuery();

        while (myRs.next()) {
            result = myRs.getBigDecimal("nilai");
            break;
        }

        return result;
    }

    private static void updateNilaiTransaksiKpeiByJenisUsaha(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger, String jenisUsahaPpe,
            String jenisUsahaMi, BigDecimal nilaiPpe, BigDecimal nilaiMi)
            throws SQLException {
        String[] kodeAkunArr = {"VD58.18", "VD58.22"};

        String updateQuery = "UPDATE public.\"Tr_VD58_KPEI\" " +
                "SET \"Nilai\"= ? " +
                "WHERE \"Tahun\"= ? AND \"Bulan\"= ? AND \"Tanggal\"= ? " +
                "AND \"KodePe\"= ? AND \"KodeAkun\"= ? ";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + updateQuery);
        PreparedStatement stmt = conn.prepareStatement(updateQuery);
        stmt.setQueryTimeout(30);
        for (String kodeAKun : kodeAkunArr) {
            if (kodeAKun.equalsIgnoreCase("VD58.18")) {
                if (!jenisUsahaPpe.equalsIgnoreCase("NONE")) {
                    stmt.setBigDecimal(1, nilaiPpe);
                } else {
                    stmt.setBigDecimal(1, new BigDecimal(0));
                }
            } else if (kodeAKun.equalsIgnoreCase("VD58.22")) {
                if (!jenisUsahaMi.equalsIgnoreCase("NONE")) {
                    stmt.setBigDecimal(1, nilaiMi);
                } else {
                    stmt.setBigDecimal(1, new BigDecimal(0));
                }
            }

            stmt.setInt(2, dto.getTahun());
            stmt.setInt(3, dto.getBulan());
            stmt.setInt(4, dto.getTanggal());
            stmt.setString(5, dto.getKodePe());
            stmt.setString(6, kodeAKun);

            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    private static List<FormulaAkun> getFormulaAkun(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws SQLException {
        List<FormulaAkun> result = new ArrayList<>();

        String query = "select kode_akun, tabel, mf.\"KdFormula\" as kd_formula, a.kolom_a as kolom " +
                "from vw_mapping_formula_akun a " +
                "join \"Ms_Formula\" mf on a.formula_a = mf.\"Id\" " +
                "where a.is_formula_a = true " +
                "union all " +
                "select kode_akun, tabel, mf.\"KdFormula\" as kd_formula, a.kolom_b as kolom " +
                "from vw_mapping_formula_akun a " +
                "join \"Ms_Formula\" mf on a.formula_b = mf.\"Id\" " +
                "where a.is_formula_b = true " +
                "union all " +
                "select kode_akun, tabel, mf.\"KdFormula\" as kd_formula, a.kolom_c as kolom " +
                "from vw_mapping_formula_akun a " +
                "join \"Ms_Formula\" mf on a.formula_c = mf.\"Id\" " +
                "where a.is_formula_c = true " +
                "union all " +
                "select kode_akun, tabel, mf.\"KdFormula\" as kd_formula, a.kolom_d as kolom " +
                "from vw_mapping_formula_akun a " +
                "join \"Ms_Formula\" mf on a.formula_d = mf.\"Id\" " +
                "where a.is_formula_d = true " +
                "union all " +
                "select kode_akun, tabel, mf.\"KdFormula\" as kd_formula, a.kolom_e as kolom " +
                "from vw_mapping_formula_akun a " +
                "join \"Ms_Formula\" mf on a.formula_e = mf.\"Id\" " +
                "where a.is_formula_e = true " +
                "union all " +
                "select kode_akun, tabel, mf.\"KdFormula\" as kd_formula, a.kolom_f as kolom " +
                "from vw_mapping_formula_akun a " +
                "join \"Ms_Formula\" mf on a.formula_f = mf.\"Id\" " +
                "where a.is_formula_f = true";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + query);
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setQueryTimeout(30);
        ResultSet myRs = stmt.executeQuery();

        while (myRs.next()) {
            result.add(FormulaAkun.builder()
                    .tabel(myRs.getString("tabel"))
                    .kodeAkun(myRs.getString("kode_akun"))
                    .kdFormula(myRs.getString("kd_formula"))
                    .kolom(myRs.getString("kolom"))
                    .build());
        }

        return result;
    }

    private static void processUpdateByFormula(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger, List<FormulaAkun> list)
            throws SQLException {
        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName + ". Please waiting...");

        int tahun = dto.getTahun();
        int bulan = dto.getBulan();
        int tanggal = dto.getTanggal();

        List<String> table59a = new ArrayList<>();
        List<String> table59b = new ArrayList<>();

        for (FormulaAkun item : list) {
            String kodeAkun = item.getKodeAkun();
            String kolom = item.getKolom();
            String viewName = "vw_" + item.getKdFormula().toLowerCase().replaceAll(" ", "") + "_kpei";

            String query1 = "update \"Tr_" + item.getTabel() + "_KPEI\" " +
                    "set \"CreatedAt\" = now(), \"" + item.getKolom() + "\" = " +
                    "(select coalesce(\"Nilai\", 0) from \"" + viewName + "\" " +
                    "where \"KodePe\" = '" + dto.getKodePe() + "' and \"Tanggal\" = "+ dto.getTanggal() + " " +
                    "and \"Bulan\" = " + dto.getBulan() + " and \"Tahun\" = " + dto.getTahun() + ") " +
                    "where \"KodeAkun\" = '" + item.getKodeAkun() + "' and \"KodePe\" = '" + dto.getKodePe() + "' " +
                    "and \"Tanggal\" = " + dto.getTanggal() + " and \"Bulan\" = " + dto.getBulan() + " " +
                    "and \"Tahun\" = " + dto.getTahun();

            String query2 = "update \"Tr_" + item.getTabel() + "_KPEI\" " +
                    "set \"" + item.getKolom() + "\" = 0 " +
                    "where \"KodeAkun\" = '" + item.getKodeAkun() + "' and \"KodePe\" = '" + dto.getKodePe() + "' " +
                    "and \"Tanggal\" = " + dto.getTanggal() + " and \"Bulan\" = " + dto.getBulan() + " " +
                    "and \"Tahun\" = " + dto.getTahun();

            String checkViewQuery = "SELECT EXISTS (" +
                    "    SELECT 1 " +
                    "    FROM pg_views " +
                    "    WHERE viewname = '" + viewName + "'" +
                    ") AS is_exists";

            logger.info("Execute query " + checkViewQuery);
            PreparedStatement stmtCheck = conn.prepareStatement(checkViewQuery);
            stmtCheck.setQueryTimeout(30);
            ResultSet checkQueryRes = stmtCheck.executeQuery();

            boolean isViewExist = false;
            while (checkQueryRes.next()) {
                isViewExist = checkQueryRes.getBoolean("is_exists");
            }

            String countRowInView = "select count(*) as total from \"" + viewName + "\" " +
                    "where \"KodePe\" = '" + dto.getKodePe() + "' " +
                    "and \"Tanggal\" = " + tanggal + " and \"Bulan\" = " + bulan + " " +
                    "and \"Tahun\" = " + tahun + ";";

            logger.info("Execute query " + countRowInView);
            PreparedStatement stmtCount = conn.prepareStatement(countRowInView);
            stmtCount.setQueryTimeout(30);

            ResultSet countQueryRes = stmtCount.executeQuery();

            int countResult = 0;
            while (countQueryRes.next()) {
                countResult = countQueryRes.getInt("total");
            }

            if (kodeAkun.equalsIgnoreCase("VD59.96") ||
                    kodeAkun.equalsIgnoreCase("VD59.97") ||
                    kodeAkun.equalsIgnoreCase("VD59.98") ||
                    kodeAkun.equalsIgnoreCase("VD59.99")) {
                table59a.add(kodeAkun + "|" + kolom + "|" + query1);
            }

            if (kodeAkun.equalsIgnoreCase("VD59.101") ||
                    kodeAkun.equalsIgnoreCase("VD59.102") ||
                    kodeAkun.equalsIgnoreCase("VD59.103") ||
                    kodeAkun.equalsIgnoreCase("VD59.104")) {
                table59b.add(kodeAkun + "|" + kolom + "|" + query1);
            }

            if (isViewExist) {
                PreparedStatement stmt = null;
                if (countResult == 1) {
                    logger.info("Execute query " + query1);
                    stmt = conn.prepareStatement(query1);
                    stmt.setQueryTimeout(30);

                } else {
                    logger.info("Execute query " + query2);
                    stmt = conn.prepareStatement(query2);
                    stmt.setQueryTimeout(30);

                }
                stmt.executeUpdate();
            }
        }

        for(String item : table59a) {
            String[] arr = item.split("\\|");
            String akun = arr[0];
            String kolom = arr[1];
            String query = arr[2];
            logger.info("Execute query " + query);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setQueryTimeout(30);

            stmt.executeUpdate();

            String queryUpdateNew = "update \"Tr_VD59_KPEI\" " +
                    "set \"" + kolom + "\" = 0 " +
                    "where \"KodeAkun\" = '" + akun + "' and \"KodePe\" = '" + dto.getKodePe() + "' " +
                    "and \"Tanggal\" = " + tanggal + " and \"Bulan\" = " + bulan + " " +
                    "and \"Tahun\" = " + tahun + " and \"" + kolom + "\" < 0";

            logger.info("Execute query " + queryUpdateNew);
            stmt = conn.prepareStatement(queryUpdateNew);
            stmt.setQueryTimeout(30);

            stmt.executeUpdate();
        }

        for(String item : table59b) {
            String[] arr = item.split("\\|");
            String query = arr[2];
            logger.info("Execute query " + query);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        }
    }

    private static void processPerhitungan(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        log.process(dto.getUsername(), dto.getFilename(), "Perhitungan Process Started");
        logger.info("=========== Perhitungan Process Started ===========");

        functionName = "Get Jenis Usaha PPE";
        String jenisUsahaPpe = getJenisUsahaPPE(conn, dto, log, logger);

        functionName = "Get Jenis Usaha MI";
        String jenisUsahaMi = getJenisUsahaMI(conn, dto, log, logger);

        String jenis = "";
        if (jenisUsahaPpe.equalsIgnoreCase("NONE") && jenisUsahaMi.equalsIgnoreCase("NONE")) {
            jenis = "NON AB";
        } else if (!jenisUsahaMi.equalsIgnoreCase("NONE")) {
            jenis = jenisUsahaPpe + jenisUsahaMi;
        } else {
            jenis = jenisUsahaPpe;
        }

        functionName = "Get NIlai Min Ppe";
        BigDecimal nilaiMinPpe = getNilaiMinPPE(conn, dto, log, logger);

        functionName = "Get NIlai Min MI";
        BigDecimal nilaiMinMi = getNilaiMinMI(conn, dto, log, logger);

        functionName = "Get NIlai Persentase Total Kewajiban";
        BigDecimal persentaseTotalKewajiban = getNilaiPersentaseTotalKewajiban(conn, dto, log, logger);

        functionName = "Get NIlai Persentase MI";
        BigDecimal persentaseMi = getNilaiPersentaseMI(conn, dto, log, logger);

        functionName = "Get NIlai MKBD Dilaporkan";
        BigDecimal mkbdDilaporkan = getNilaiMkbdDilaporkan(conn, dto, log, logger);

        functionName = "Get NIlai MKBD Dipersyaratkan";
        BigDecimal mkbdDipersyaratkan = getNilaiMkbdDipersyaratkan(conn, dto, log, logger);

        functionName = "Get Count Row Perhitungan MKBD";
        Integer countRowPerhitunganMkbd = getCountPerhitunganMKBD(conn, dto, log, logger);

        Integer memenuhi = 0;
        if (mkbdDilaporkan.compareTo(mkbdDipersyaratkan) > 0) {
            memenuhi = 1;
        }

        functionName = "Insert or Update Perhitungan MKBD";
        processInsertOrUpdatePerhitunganMKBD(conn, dto, log, logger,
                nilaiMinPpe, nilaiMinMi, persentaseTotalKewajiban,
                persentaseMi, mkbdDilaporkan, mkbdDipersyaratkan, memenuhi, jenis, countRowPerhitunganMkbd);

        log.process(dto.getUsername(), dto.getFilename(), "Perhitungan Process Finished");
        logger.info("=========== Perhitungan Process finished ===========");
    }

    private static BigDecimal getNilaiMinPPE(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws SQLException {
        BigDecimal result = null;
        String query = "select \"Nilai\" as nilai from \"Tr_VD58_KPEI\" " +
                "WHERE \"Tahun\"= " + dto.getTahun() + " AND \"Bulan\"= " + dto.getBulan() + " " +
                "AND \"Tanggal\"= " + dto.getTanggal() + " " +
                "AND \"KodePe\"= '" + dto.getKodePe() + "' AND \"KodeAkun\"= 'VD58.18' ";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + query);
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setQueryTimeout(30);

        ResultSet myRs = stmt.executeQuery();

        while (myRs.next()) {
            result = myRs.getBigDecimal("nilai");
        }

        return result;
    }

    private static BigDecimal getNilaiMinMI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws SQLException {
        BigDecimal result = null;
        String query = "select \"Nilai\" as nilai from \"Tr_VD58_KPEI\" " +
                "WHERE \"Tahun\"= " + dto.getTahun() + " AND \"Bulan\"= " + dto.getBulan() + " " +
                "AND \"Tanggal\"= " + dto.getTanggal() + " " +
                "AND \"KodePe\"= '" + dto.getKodePe() + "' AND \"KodeAkun\"= 'VD58.22' ";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + query);
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setQueryTimeout(30);

        ResultSet myRs = stmt.executeQuery();

        while (myRs.next()) {
            result = myRs.getBigDecimal("nilai");
        }

        return result;
    }

    private static BigDecimal getNilaiPersentaseTotalKewajiban(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws SQLException {
        BigDecimal result = null;
        String query = "select \"Nilai\" as nilai from \"Tr_VD58_KPEI\" " +
                "WHERE \"Tahun\"= " + dto.getTahun() + " AND \"Bulan\"= " + dto.getBulan() + " " +
                "AND \"Tanggal\"= " + dto.getTanggal() + " " +
                "AND \"KodePe\"= '" + dto.getKodePe() + "' AND \"KodeAkun\"= 'VD58.19' ";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + query);
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setQueryTimeout(30);

        ResultSet myRs = stmt.executeQuery();

        while (myRs.next()) {
            result = myRs.getBigDecimal("nilai");
        }

        return result;
    }

    private static BigDecimal getNilaiPersentaseMI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws SQLException {
        BigDecimal result = null;
        String query = "select \"Nilai\" as nilai from \"Tr_VD58_KPEI\" " +
                "WHERE \"Tahun\"= " + dto.getTahun() + " AND \"Bulan\"= " + dto.getBulan() + " " +
                "AND \"Tanggal\"= " + dto.getTanggal() + " " +
                "AND \"KodePe\"= '" + dto.getKodePe() + "' AND \"KodeAkun\"= 'VD58.24' ";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + query);
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setQueryTimeout(30);

        ResultSet myRs = stmt.executeQuery();

        while (myRs.next()) {
            result = myRs.getBigDecimal("nilai");
        }

        return result;
    }

    private static BigDecimal getNilaiMkbdDilaporkan(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws SQLException {
        BigDecimal result = null;
        String query = "select \"Total\" as nilai from \"Tr_VD59_KPEI\" " +
                "WHERE \"Tahun\"= " + dto.getTahun() + " AND \"Bulan\"= " + dto.getBulan() + " " +
                "AND \"Tanggal\"= " + dto.getTanggal() + " " +
                "AND \"KodePe\"= '" + dto.getKodePe() + "' AND \"KodeAkun\"= 'VD59.102' ";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + query);
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setQueryTimeout(30);

        ResultSet myRs = stmt.executeQuery();

        while (myRs.next()) {
            result = myRs.getBigDecimal("nilai");
        }

        return result;
    }

    private static BigDecimal getNilaiMkbdDipersyaratkan(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws SQLException {
        BigDecimal result = null;
        String query = "select \"Nilai\" as nilai from \"Tr_VD58_KPEI\" " +
                "WHERE \"Tahun\"= " + dto.getTahun() + " AND \"Bulan\"= " + dto.getBulan() + " " +
                "AND \"Tanggal\"= " + dto.getTanggal() + " " +
                "AND \"KodePe\"= '" + dto.getKodePe() + "' AND \"KodeAkun\"= 'VD58.26' ";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + query);
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setQueryTimeout(30);

        ResultSet myRs = stmt.executeQuery();

        while (myRs.next()) {
            result = myRs.getBigDecimal("nilai");
        }

        return result;
    }

    private static Integer getCountPerhitunganMKBD(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws SQLException {
        Integer result = null;
        String query = "select count(*) as amount from \"Ms_PerhitunganMkbd\" " +
                "WHERE \"Tahun\"= " + dto.getTahun() + " AND \"Bulan\"= " + dto.getBulan() + " " +
                "AND \"Tanggal\"= " + dto.getTanggal() + " " +
                "AND \"KodePe\"= '" + dto.getKodePe() + "'";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + query);
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setQueryTimeout(30);

        ResultSet myRs = stmt.executeQuery();

        while (myRs.next()) {
            result = myRs.getInt("amount");
        }

        return result;
    }

    private static void processInsertOrUpdatePerhitunganMKBD(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger,
            BigDecimal minPe, BigDecimal minMi, BigDecimal persentaseTotalKewajiban,
            BigDecimal persentaseMi, BigDecimal mkbdDilaporkan, BigDecimal mkbdDipersyaratkan,
            Integer memenuhi, String jenisUsaha, Integer countRow
    ) throws Exception {
        if (countRow > 0) {
            log.process(dto.getUsername(), dto.getFilename(), functionName + " - Update applied");
            logger.info("Process " + functionName + " - Update applied");
            String query = "UPDATE public.\"Ms_PerhitunganMkbd\" " +
                    "SET \"JenisUsaha\"=?, \"MinMKBD_PE\"=?, \"MinMKBD_MI\"=?, \"Persentase_TotalKewajiban\"=?, " +
                    "\"Persentase_TotalDanaMI\"=?, \"MKBD_Dilaporkan\"=?, \"MKBD_Dipersyaratkan\"=?, " +
                    "\"Memenuhi\"=?, \"ModifiedAt\"=?, \"ModifiedBy\"=? " +
                    "WHERE \"KodePe\"=? and \"Tahun\"=? and \"Bulan\"=? and \"Tanggal\"=?";

            logger.info("Execute query " + query);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setQueryTimeout(30);

            stmt.setString(1, jenisUsaha);
            stmt.setBigDecimal(2, minPe);
            stmt.setBigDecimal(3, minMi);
            stmt.setBigDecimal(4, persentaseTotalKewajiban);
            stmt.setBigDecimal(5, persentaseMi);
            stmt.setBigDecimal(6, mkbdDilaporkan);
            stmt.setBigDecimal(7, mkbdDipersyaratkan);
            stmt.setInt(8, memenuhi);
            stmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(10, dto.getUserId());
            stmt.setString(11, dto.getKodePe());
            stmt.setInt(12, dto.getTahun());
            stmt.setInt(13, dto.getBulan());
            stmt.setInt(14, dto.getTanggal());

            stmt.executeUpdate();
        } else {
            log.process(dto.getUsername(), dto.getFilename(), functionName + " - Insert applied");
            logger.info("Process " + functionName + " - Insert applied");
            String query = "INSERT INTO public.\"Ms_PerhitunganMkbd\" " +
                    "(\"Id\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                    "\"JenisUsaha\", \"MinMKBD_PE\", \"MinMKBD_MI\", \"Persentase_TotalKewajiban\", " +
                    "\"Persentase_TotalDanaMI\", \"MKBD_Dilaporkan\", \"MKBD_Dipersyaratkan\", \"Memenuhi\", " +
                    "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            logger.info("Execute query " + query);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setQueryTimeout(30);

            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, dto.getKodePe());
            stmt.setInt(3, dto.getTahun());
            stmt.setInt(4, dto.getBulan());
            stmt.setInt(5, dto.getTanggal());
            stmt.setString(6, jenisUsaha);
            stmt.setBigDecimal(7, minPe);
            stmt.setBigDecimal(8, minMi);
            stmt.setBigDecimal(9, persentaseTotalKewajiban);
            stmt.setBigDecimal(10, persentaseMi);
            stmt.setBigDecimal(11, mkbdDilaporkan);
            stmt.setBigDecimal(12, mkbdDipersyaratkan);
            stmt.setInt(13, memenuhi);
            stmt.setTimestamp(14, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(15, dto.getUserId());
            stmt.setTimestamp(16, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(17, dto.getUserId());

            stmt.executeUpdate();
        }

    }

    private static void processHistorical(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        log.process(dto.getUsername(), dto.getFilename(), "Historical Process Started");
        logger.info("=========== Historical Process Started ===========");

        functionName = "Insert VD51 History Data";
        processVd51Hist(conn, dto, log, logger);

        functionName = "Insert VD52 History Data";
        processVd52Hist(conn, dto, log, logger);

        functionName = "Insert VD53 History Data";
        processVd53Hist(conn, dto, log, logger);

        functionName = "Insert VD54 History Data";
        processVd54Hist(conn, dto, log, logger);

        functionName = "Insert VD55 History Data";
        processVd55Hist(conn, dto, log, logger);

        functionName = "Insert VD56A History Data";
        processVd56AHist(conn, dto, log, logger);

        functionName = "Insert VD56B History Data";
        processVd56BHist(conn, dto, log, logger);

        functionName = "Insert VD56C History Data";
        processVd56CHist(conn, dto, log, logger);

        functionName = "Insert VD57A History Data";
        processVd57AHist(conn, dto, log, logger);

        functionName = "Insert VD57B History Data";
        processVd57BHist(conn, dto, log, logger);

        functionName = "Insert VD57C History Data";
        processVd57CHist(conn, dto, log, logger);

        functionName = "Insert VD58 History Data";
        processVd58Hist(conn, dto, log, logger);

        functionName = "Insert VD59 History Data";
        processVd59Hist(conn, dto, log, logger);

        functionName = "Insert VD510A History Data";
        processVd510AHist(conn, dto, log, logger);

        functionName = "Insert VD510B History Data";
        processVd510BHist(conn, dto, log, logger);

        functionName = "Insert VD510C History Data";
        processVd510CHist(conn, dto, log, logger);

        functionName = "Insert VD510D History Data";
        processVd510DHist(conn, dto, log, logger);

        functionName = "Insert VD510E History Data";
        processVd510EHist(conn, dto, log, logger);

        functionName = "Insert VD510F History Data";
        processVd510FHist(conn, dto, log, logger);

        functionName = "Insert VD510G History Data";
        processVd510GHist(conn, dto, log, logger);

        functionName = "Insert VD510H History Data";
        processVd510HHist(conn, dto, log, logger);

        functionName = "Insert VD510I History Data";
        processVd510IHist(conn, dto, log, logger);

        log.process(dto.getUsername(), dto.getFilename(), "Historical Process Finished");
        logger.info("=========== Historical Process finished ===========");
    }

    private static void processVd51Hist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD51_Hist\" " +
                "(\"Id\", \"Saldo\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", \"KodeAkun\", \"CreatedAt\", " +
                "\"ModifiedAt\", \"CreatedBy\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Saldo\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", \"KodeAkun\", \"CreatedAt\", " +
                "\"ModifiedAt\", \"CreatedBy\", \"ModifiedBy\" " +
                "FROM \"Tr_VD51\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd52Hist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD52_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD52\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd53Hist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD53_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"NilaiDitambahkan\", \"KodeAkun\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"NilaiDitambahkan\", \"KodeAkun\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD53\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd54Hist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD54_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"KodeJenisReksadana\", " +
                "\"NamaJenisReksadana\", \"IsAfiliasi\", \"NilaiAktivaBersihUnit\", \"NilaiAktivaBersihReksadana\", " +
                "\"BatasanMkbd\", \"KelebihanMkbd\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"KodeJenisReksadana\", " +
                "\"NamaJenisReksadana\", \"IsAfiliasi\", \"NilaiAktivaBersihUnit\", \"NilaiAktivaBersihReksadana\", " +
                "\"BatasanMkbd\", \"KelebihanMkbd\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD54\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd55Hist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD55_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"KodeEfek\", \"NilaiEfek\", " +
                "\"KodeEfekLN\", \"NilaiEfekLN\", \"NilaiEfekTutupLN\", \"NilaiHaircutTutupLN\", \"NilaiHaircutLN\", " +
                "\"JmlPengembalianHaircut\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"KodeEfek\", \"NilaiEfek\", " +
                "\"KodeEfekLN\", \"NilaiEfekLN\", \"NilaiEfekTutupLN\", \"NilaiHaircutTutupLN\", \"NilaiHaircutLN\", " +
                "\"JmlPengembalianHaircut\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD55\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd56AHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD56A_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Terafiliasi\", " +
                "\"TidakTerafiliasi\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Terafiliasi\", " +
                "\"TidakTerafiliasi\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD56A\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd56BHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD56B_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Dimiliki\", \"Dipisahkan\", " +
                "\"TidakDipisahkan\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Dimiliki\", \"Dipisahkan\", " +
                "\"TidakDipisahkan\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD56B\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd56CHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD56C_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"KodeBank\", \"Saldo\", " +
                "\"IsSendiriNasabah\", \"NoRekening\", \"KodeCurrency\", \"SaldoRupiah\", \"Penjelasan\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"KodeBank\", \"Saldo\", " +
                "\"IsSendiriNasabah\", \"NoRekening\", \"KodeCurrency\", \"SaldoRupiah\", \"Penjelasan\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD56C\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd57AHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD57A_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Terafiliasi\", " +
                "\"TidakTerafiliasi\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Terafiliasi\", " +
                "\"TidakTerafiliasi\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD57A\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd57BHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD57B_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Dimiliki\", " +
                "\"Dipisahkan\", \"TidakDipisahkan\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", " +
                "\"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Dimiliki\", " +
                "\"Dipisahkan\", \"TidakDipisahkan\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", " +
                "\"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD57B\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd57CHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD57C_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"LmHrKerja\", " +
                "\"Dimiliki\", \"Dipisahkan\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", " +
                "\"ModifiedAt\", \"ModifiedBy\", \"Penjelasan\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"LmHrKerja\", " +
                "\"Dimiliki\", \"Dipisahkan\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", " +
                "\"ModifiedAt\", \"ModifiedBy\", \"Penjelasan\" " +
                "FROM \"Tr_VD57C\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd58Hist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD58_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Nilai\", \"KodeAkun\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Nilai\", \"KodeAkun\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD58\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd59Hist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD59_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"Jumlah\", " +
                "\"Total\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"Jumlah\", " +
                "\"Total\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD59\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510AHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510A_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Pembeli\", \"TanggalPembelian\", \"TanggalPenjualan\", \"NilaiPembelian\", \"NilaiPenjualan\", " +
                "\"KodeEfekKolateral\", \"JumlahJaminan\", \"NilaiPasarWajar\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\", \"Prev_ID\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Pembeli\", \"TanggalPembelian\", \"TanggalPenjualan\", \"NilaiPembelian\", \"NilaiPenjualan\", " +
                "\"KodeEfekKolateral\", \"JumlahJaminan\", \"NilaiPasarWajar\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\", \"Prev_ID\" " +
                "FROM \"Tr_VD510A\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510BHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510B_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Penjual\", \"TanggalPembelian\", \"TanggalPenjualan\", \"NilaiPembelian\", \"NilaiPenjualan\", " +
                "\"KodeEfekKolateral\", \"JumlahJaminan\", \"NilaiPasarWajar\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Penjual\", \"TanggalPembelian\", \"TanggalPenjualan\", \"NilaiPembelian\", \"NilaiPenjualan\", " +
                "\"KodeEfekKolateral\", \"JumlahJaminan\", \"NilaiPasarWajar\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510B\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510CHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510C_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Terafiliasi\", \"GrupEmiten\", \"PersentaseNilaiPasar\", \"LembarNominal\", " +
                "\"HargaPerolehan\", \"HargaPasarWajar\", \"NilaiPasarWajar\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Terafiliasi\", \"GrupEmiten\", \"PersentaseNilaiPasar\", \"LembarNominal\", " +
                "\"HargaPerolehan\", \"HargaPasarWajar\", \"NilaiPasarWajar\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510C\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510DHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510D_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"NamaNasabah\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"MarginSelling\", \"NilaiPembiayaan\", \"NilaiJaminan\", \"RasioPembiayaan\", " +
                "\"NilaiRankingLiabilitiesNasabah\", \"NilaiRankingLiabilitiesRasio\", \"CreatedAt\", " +
                "\"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"NamaNasabah\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"MarginSelling\", \"NilaiPembiayaan\", \"NilaiJaminan\", \"RasioPembiayaan\", " +
                "\"NilaiRankingLiabilitiesNasabah\", \"NilaiRankingLiabilitiesRasio\", \"CreatedAt\", " +
                "\"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510D\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510EHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510E_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Volume\", \"Harga\", \"NilaiPasarWajar\", \"CreatedAt\", \"CreatedBy\", " +
                "\"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Volume\", \"Harga\", \"NilaiPasarWajar\", \"CreatedAt\", \"CreatedBy\", " +
                "\"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510E\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510FHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510F_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", \"TanggalKontrak\", " +
                "\"JenisPenjaminan\", \"PihakDijamin\", \"StatuPenjaminan\", \"NilaiKomitmenPenjaminan\", " +
                "\"HaircutAtasEfek\", \"NilaiBelumTerserap\", \"NilaiBankGaransi\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", \"TanggalKontrak\", " +
                "\"JenisPenjaminan\", \"PihakDijamin\", \"StatuPenjaminan\", \"NilaiKomitmenPenjaminan\", " +
                "\"HaircutAtasEfek\", \"NilaiBelumTerserap\", \"NilaiBankGaransi\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510F\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510GHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510G_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"TanggalKontrak\", \"Terafiliasi\", \"PihakDijamin\", \"RincianPenjaminan\", " +
                "\"JangkaWaktuPnejaminan\", \"TanggalBerakhir\", \"NilaiPenjaminan\", " +
                "\"NilaiRankingLiabilitas\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"TanggalKontrak\", \"Terafiliasi\", \"PihakDijamin\", \"RincianPenjaminan\", " +
                "\"JangkaWaktuPnejaminan\", \"TanggalBerakhir\", \"NilaiPenjaminan\", " +
                "\"NilaiRankingLiabilitas\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510G\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510HHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510H_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"TanggalKomitmen\", \"RincianBelanja\", \"TanggalRealisasi\", \"RincianPenjaminan\", " +
                "\"KomitmenTerealisasi\", \"KomitmenBelumTerealisasi\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"TanggalKomitmen\", \"RincianBelanja\", \"TanggalRealisasi\", \"RincianPenjaminan\", " +
                "\"KomitmenTerealisasi\", \"KomitmenBelumTerealisasi\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510H\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510IHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510I_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"TanggalTransaksi\", \"JenisTransaksi\", \"JenisMataUang\", \"NilaiTransaksi\", " +
                "\"UntungRugiBelumTerealisasi\", \"NilaiRankingLiabilitas\", \"CreatedAt\", " +
                "\"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"TanggalTransaksi\", \"JenisTransaksi\", \"JenisMataUang\", \"NilaiTransaksi\", " +
                "\"UntungRugiBelumTerealisasi\", \"NilaiRankingLiabilitas\", \"CreatedAt\", " +
                "\"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510I\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processHistoricalKPEI(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger)
            throws Exception {
        log.process(dto.getUsername(), dto.getFilename(), "Historical KPEI Process Started");
        logger.info("=========== Historical KPEI Process Started ===========");

        functionName = "Insert VD51 KPEI History Data";
        processVd51KpeiHist(conn, dto, log, logger);

        functionName = "Insert VD52 KPEI History Data";
        processVd52KpeiHist(conn, dto, log, logger);

        functionName = "Insert VD53 KPEI History Data";
        processVd53KpeiHist(conn, dto, log, logger);

        functionName = "Insert VD54 KPEI History Data";
        processVd54KpeiHist(conn, dto, log, logger);

        functionName = "Insert VD55 KPEI History Data";
        processVd55KpeiHist(conn, dto, log, logger);

        functionName = "Insert VD56A KPEI History Data";
        processVd56AKpeiHist(conn, dto, log, logger);

        functionName = "Insert VD56B KPEI History Data";
        processVd56BKpeiHist(conn, dto, log, logger);

        functionName = "Insert VD56C KPEI History Data";
        processVd56CKpeiHist(conn, dto, log, logger);

        functionName = "Insert VD57A KPEI History Data";
        processVd57AKpeiHist(conn, dto, log, logger);

        functionName = "Insert VD57B KPEI History Data";
        processVd57BKpeiHist(conn, dto, log, logger);

        functionName = "Insert VD57C KPEI History Data";
        processVd57CKpeiHist(conn, dto, log, logger);

        functionName = "Insert VD58 KPEI History Data";
        processVd58KpeiHist(conn, dto, log, logger);

        functionName = "Insert VD59 KPEI History Data";
        processVd59KpeiHist(conn, dto, log, logger);

        functionName = "Insert VD510A KPEI History Data";
        processVd510AKpeiHist(conn, dto, log, logger);

        functionName = "Insert VD510B KPEI History Data";
        processVd510BKpeiHist(conn, dto, log, logger);

        functionName = "Insert VD510C KPEI History Data";
        processVd510CKpeiHist(conn, dto, log, logger);

        functionName = "Insert VD510D KPEI History Data";
        processVd510DKpeiHist(conn, dto, log, logger);

        functionName = "Insert VD510E KPEI History Data";
        processVd510EKpeiHist(conn, dto, log, logger);

        functionName = "Insert VD510F KPEI History Data";
        processVd510FKpeiHist(conn, dto, log, logger);

        functionName = "Insert VD510G KPEI History Data";
        processVd510GKpeiHist(conn, dto, log, logger);

        functionName = "Insert VD510H KPEI History Data";
        processVd510HKpeiHist(conn, dto, log, logger);

        functionName = "Insert VD510I KPEI History Data";
        processVd510IKpeiHist(conn, dto, log, logger);

        log.process(dto.getUsername(), dto.getFilename(), "Historical KPEI Process Finished");
        logger.info("=========== Historical KPEI Process finished ===========");
    }

    private static void processVd51KpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD51_KPEI_Hist\" " +
                "(\"Id\", \"Saldo\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", \"KodeAkun\", \"CreatedAt\", " +
                "\"ModifiedAt\", \"CreatedBy\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Saldo\", \"Tanggal\", \"Bulan\", \"Tahun\", \"KodePe\", \"KodeAkun\", \"CreatedAt\", " +
                "\"ModifiedAt\", \"CreatedBy\", \"ModifiedBy\" " +
                "FROM \"Tr_VD51_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd52KpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD52_KPEI_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"KodeAkun\", \"CreatedAt\", " +
                "\"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD52_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd53KpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD53_KPEI_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"NilaiDitambahkan\", \"KodeAkun\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"NilaiDitambahkan\", \"KodeAkun\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD53_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd54KpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD54_KPEI_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"KodeJenisReksadana\", " +
                "\"NamaJenisReksadana\", \"IsAfiliasi\", \"NilaiAktivaBersihUnit\", \"NilaiAktivaBersihReksadana\", " +
                "\"BatasanMkbd\", \"KelebihanMkbd\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"KodeJenisReksadana\", " +
                "\"NamaJenisReksadana\", \"IsAfiliasi\", \"NilaiAktivaBersihUnit\", \"NilaiAktivaBersihReksadana\", " +
                "\"BatasanMkbd\", \"KelebihanMkbd\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD54_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd55KpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD55_KPEI_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"KodeEfek\", \"NilaiEfek\", " +
                "\"KodeEfekLN\", \"NilaiEfekLN\", \"NilaiEfekTutupLN\", \"NilaiHaircutTutupLN\", \"NilaiHaircutLN\", " +
                "\"JmlPengembalianHaircut\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"KodeEfek\", \"NilaiEfek\", " +
                "\"KodeEfekLN\", \"NilaiEfekLN\", \"NilaiEfekTutupLN\", \"NilaiHaircutTutupLN\", \"NilaiHaircutLN\", " +
                "\"JmlPengembalianHaircut\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD55_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd56AKpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD56A_KPEI_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Terafiliasi\", " +
                "\"TidakTerafiliasi\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Terafiliasi\", " +
                "\"TidakTerafiliasi\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD56A_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd56BKpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD56B_KPEI_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Dimiliki\", \"Dipisahkan\", " +
                "\"TidakDipisahkan\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Dimiliki\", \"Dipisahkan\", " +
                "\"TidakDipisahkan\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD56B_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd56CKpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD56C_KPEI_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"KodeBank\", \"Saldo\", " +
                "\"IsSendiriNasabah\", \"NoRekening\", \"KodeCurrency\", \"SaldoRupiah\", \"Penjelasan\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"KodeBank\", \"Saldo\", " +
                "\"IsSendiriNasabah\", \"NoRekening\", \"KodeCurrency\", \"SaldoRupiah\", \"Penjelasan\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD56C_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd57AKpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD57A_KPEI_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Terafiliasi\", " +
                "\"TidakTerafiliasi\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Terafiliasi\", " +
                "\"TidakTerafiliasi\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD57A_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd57BKpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD57B_KPEI_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Dimiliki\", " +
                "\"Dipisahkan\", \"TidakDipisahkan\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", " +
                "\"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"Dimiliki\", " +
                "\"Dipisahkan\", \"TidakDipisahkan\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", " +
                "\"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD57B_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd57CKpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD57C_KPEI_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"LmHrKerja\", " +
                "\"Dimiliki\", \"Dipisahkan\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", " +
                "\"ModifiedAt\", \"ModifiedBy\", \"Penjelasan\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Saldo\", \"LmHrKerja\", " +
                "\"Dimiliki\", \"Dipisahkan\", \"KodeAkun\", \"CreatedAt\", \"CreatedBy\", " +
                "\"ModifiedAt\", \"ModifiedBy\", \"Penjelasan\" " +
                "FROM \"Tr_VD57C_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd58KpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD58_KPEI_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Nilai\", \"KodeAkun\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"Nilai\", \"KodeAkun\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD58_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd59KpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD59_KPEI_Hist\" " +
                "(\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"Jumlah\", " +
                "\"Total\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"Tahun\", \"Bulan\", \"Tanggal\", \"KodePe\", \"KodeAkun\", \"Jumlah\", " +
                "\"Total\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD59_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510AKpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510A_KPEI_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Pembeli\", \"TanggalPembelian\", \"TanggalPenjualan\", \"NilaiPembelian\", \"NilaiPenjualan\", " +
                "\"KodeEfekKolateral\", \"JumlahJaminan\", \"NilaiPasarWajar\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\", \"Prev_ID\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Pembeli\", \"TanggalPembelian\", \"TanggalPenjualan\", \"NilaiPembelian\", \"NilaiPenjualan\", " +
                "\"KodeEfekKolateral\", \"JumlahJaminan\", \"NilaiPasarWajar\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\", \"Prev_ID\" " +
                "FROM \"Tr_VD510A_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510BKpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510B_KPEI_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Penjual\", \"TanggalPembelian\", \"TanggalPenjualan\", \"NilaiPembelian\", \"NilaiPenjualan\", " +
                "\"KodeEfekKolateral\", \"JumlahJaminan\", \"NilaiPasarWajar\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Penjual\", \"TanggalPembelian\", \"TanggalPenjualan\", \"NilaiPembelian\", \"NilaiPenjualan\", " +
                "\"KodeEfekKolateral\", \"JumlahJaminan\", \"NilaiPasarWajar\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510B_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510CKpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510C_KPEI_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Terafiliasi\", \"GrupEmiten\", \"PersentaseNilaiPasar\", \"LembarNominal\", " +
                "\"HargaPerolehan\", \"HargaPasarWajar\", \"NilaiPasarWajar\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Terafiliasi\", \"GrupEmiten\", \"PersentaseNilaiPasar\", \"LembarNominal\", " +
                "\"HargaPerolehan\", \"HargaPasarWajar\", \"NilaiPasarWajar\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510C_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510DKpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510D_KPEI_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"NamaNasabah\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"MarginSelling\", \"NilaiPembiayaan\", \"NilaiJaminan\", \"RasioPembiayaan\", " +
                "\"NilaiRankingLiabilitiesNasabah\", \"NilaiRankingLiabilitiesRasio\", \"CreatedAt\", " +
                "\"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"NamaNasabah\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"MarginSelling\", \"NilaiPembiayaan\", \"NilaiJaminan\", \"RasioPembiayaan\", " +
                "\"NilaiRankingLiabilitiesNasabah\", \"NilaiRankingLiabilitiesRasio\", \"CreatedAt\", " +
                "\"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510D_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510EKpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510E_KPEI_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Volume\", \"Harga\", \"NilaiPasarWajar\", \"CreatedAt\", \"CreatedBy\", " +
                "\"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"KodeEfek\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"Volume\", \"Harga\", \"NilaiPasarWajar\", \"CreatedAt\", \"CreatedBy\", " +
                "\"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510E_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510FKpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510F_KPEI_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", \"TanggalKontrak\", " +
                "\"JenisPenjaminan\", \"PihakDijamin\", \"StatuPenjaminan\", \"NilaiKomitmenPenjaminan\", " +
                "\"HaircutAtasEfek\", \"NilaiBelumTerserap\", \"NilaiBankGaransi\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", \"TanggalKontrak\", " +
                "\"JenisPenjaminan\", \"PihakDijamin\", \"StatuPenjaminan\", \"NilaiKomitmenPenjaminan\", " +
                "\"HaircutAtasEfek\", \"NilaiBelumTerserap\", \"NilaiBankGaransi\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510F_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510GKpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510G_KPEI_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"TanggalKontrak\", \"Terafiliasi\", \"PihakDijamin\", \"RincianPenjaminan\", " +
                "\"JangkaWaktuPnejaminan\", \"TanggalBerakhir\", \"NilaiPenjaminan\", " +
                "\"NilaiRankingLiabilitas\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"TanggalKontrak\", \"Terafiliasi\", \"PihakDijamin\", \"RincianPenjaminan\", " +
                "\"JangkaWaktuPnejaminan\", \"TanggalBerakhir\", \"NilaiPenjaminan\", " +
                "\"NilaiRankingLiabilitas\", \"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510G_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510HKpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510H_KPEI_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"TanggalKomitmen\", \"RincianBelanja\", \"TanggalRealisasi\", \"RincianPenjaminan\", " +
                "\"KomitmenTerealisasi\", \"KomitmenBelumTerealisasi\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"TanggalKomitmen\", \"RincianBelanja\", \"TanggalRealisasi\", \"RincianPenjaminan\", " +
                "\"KomitmenTerealisasi\", \"KomitmenBelumTerealisasi\", \"NilaiRankingLiabilitas\", " +
                "\"CreatedAt\", \"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510H_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void processVd510IKpeiHist(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger) throws SQLException {
        String insertQuery = "INSERT INTO \"Tr_VD510I_KPEI_Hist\" " +
                "(\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"TanggalTransaksi\", \"JenisTransaksi\", \"JenisMataUang\", \"NilaiTransaksi\", " +
                "\"UntungRugiBelumTerealisasi\", \"NilaiRankingLiabilitas\", \"CreatedAt\", " +
                "\"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\") " +
                "(SELECT " +
                "\"Id\", \"KodeAkun\", \"KodePe\", \"Tahun\", \"Bulan\", \"Tanggal\", " +
                "\"TanggalTransaksi\", \"JenisTransaksi\", \"JenisMataUang\", \"NilaiTransaksi\", " +
                "\"UntungRugiBelumTerealisasi\", \"NilaiRankingLiabilitas\", \"CreatedAt\", " +
                "\"CreatedBy\", \"ModifiedAt\", \"ModifiedBy\" " +
                "FROM \"Tr_VD510I_KPEI\" a where a.\"Tanggal\" = ? and a.\"Bulan\" = ? and a.\"Tahun\" = ? and \"KodePe\" = ?)";

        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);
        logger.info("Execute query " + insertQuery);
        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setQueryTimeout(30);

        stmt.setInt(1, dto.getTanggal());
        stmt.setInt(2, dto.getBulan());
        stmt.setInt(3, dto.getTahun());
        stmt.setString(4, dto.getKodePe());
        stmt.executeUpdate();
    }

    public static void processSaveLog(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger, Integer status) {
        try {
            conn.setAutoCommit(false);

            functionName = "Save Log";
            saveLog(conn, dto, log, logger, status);

            conn.commit();
        } catch (Exception e) {
            try {
                log.error(dto.getUsername(), dto.getFilename(), functionName, e.getMessage());
                logger.error("Failed to save log because : " + e.getMessage());
                conn.rollback();
            } catch (Exception ex) {
                logger.error("Failed to rollback because : " + e.getMessage());
            }
        }
    }

    private static Integer countLog(Connection conn, MkbTransformDto dto)
            throws Exception {
        String query = "Select count(*) as total from \"TableLog\" where \"NamaFile\" = '" + dto.getFilename() + "'";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setQueryTimeout(30);

        ResultSet res = stmt.executeQuery();

        Integer result = 0;

        while (res.next()) {
            result = res.getInt("total");
            break;
        }

        return result;
    }

    private static void saveLog(
            Connection conn, MkbTransformDto dto, LogUtil log, Logger logger, Integer status)
            throws Exception {
        log.process(dto.getUsername(), dto.getFilename(), functionName);
        logger.info("Process " + functionName);

        Integer count = countLog(conn, dto);

        if (count == 0) {
            String insertQuery = "INSERT INTO \"TableLog\" " +
                    "(\"NamaFile\", \"Waktu\", \"IsValid\") " +
                    "VALUES(?, ?, ?)";

            logger.info("Execute query " + insertQuery);
            PreparedStatement stmt = conn.prepareStatement(insertQuery);
            stmt.setQueryTimeout(30);

            stmt.setString(1, dto.getFilename());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(3, status);
            stmt.executeUpdate();
        } else {
            String updateQuery = "update \"TableLog\" " +
                    "set \"Waktu\" = ?, \"IsValid\" = ? where \"NamaFile\" = ?";

            logger.info("Execute query " + updateQuery);
            PreparedStatement stmt = conn.prepareStatement(updateQuery);
            stmt.setQueryTimeout(30);

            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(2, status);
            stmt.setString(3, dto.getFilename());
            stmt.executeUpdate();
        }

    }

    private static void setLogPengirimanActive(Connection conn, MkbTransformDto dto) throws SQLException {
        String updateQuery = "UPDATE \"LogPengiriman\" " +
                "SET \"IsActive\" = true, \"ModifiedAt\" = now(), \"ModifiedBy\" = ? " +
                "WHERE \"Tanggal\" = ? and \"Bulan\" = ? and \"Tahun\" = ? and \"KodePe\" = ?";

        PreparedStatement stmt = conn.prepareStatement(updateQuery);
        stmt.setQueryTimeout(30);

        stmt.setString(1, dto.getUserId());
        stmt.setInt(2, dto.getTanggal());
        stmt.setInt(3, dto.getBulan());
        stmt.setInt(4, dto.getTahun());
        stmt.setString(5, dto.getKodePe());
        stmt.executeUpdate();
    }

    private static void cleansingDataIfExist(Connection conn, MkbTransformDto dto, Logger logger) throws SQLException {
        logger.info("Trying to delete existing data");

        for (String table : getListTable()) {
            String deleteQuery = "DELETE from \""+ table +"\" " +
                    "WHERE \"Tanggal\" = ? and \"Bulan\" = ? and \"Tahun\" = ? and \"KodePe\" = ?";
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);
            stmt.setQueryTimeout(30);

            stmt.setInt(1, dto.getTanggal());
            stmt.setInt(2, dto.getBulan());
            stmt.setInt(3, dto.getTahun());
            stmt.setString(4, dto.getKodePe());
            stmt.addBatch();
            stmt.executeBatch();

            logger.info("Successfully deleted existing data from table " + table);
        }

    }

    private static List<String> getListTable() {
        List<String> listTable = new ArrayList<>();

        listTable.add("Tr_VD51");
        listTable.add("Tr_VD52");
        listTable.add("Tr_VD53");
        listTable.add("Tr_VD54");
        listTable.add("Tr_VD55");
        listTable.add("Tr_VD56A");
        listTable.add("Tr_VD56B");
        listTable.add("Tr_VD56C");
        listTable.add("Tr_VD57A");
        listTable.add("Tr_VD57B");
        listTable.add("Tr_VD57C");
        listTable.add("Tr_VD58");
        listTable.add("Tr_VD59");
        listTable.add("Tr_VD510A");
        listTable.add("Tr_VD510B");
        listTable.add("Tr_VD510C");
        listTable.add("Tr_VD510D");
        listTable.add("Tr_VD510E");
        listTable.add("Tr_VD510F");
        listTable.add("Tr_VD510G");
        listTable.add("Tr_VD510H");
        listTable.add("Tr_VD510I");

        listTable.add("Tr_VD51_Hist");
        listTable.add("Tr_VD52_Hist");
        listTable.add("Tr_VD53_Hist");
        listTable.add("Tr_VD54_Hist");
        listTable.add("Tr_VD55_Hist");
        listTable.add("Tr_VD56A_Hist");
        listTable.add("Tr_VD56B_Hist");
        listTable.add("Tr_VD56C_Hist");
        listTable.add("Tr_VD57A_Hist");
        listTable.add("Tr_VD57B_Hist");
        listTable.add("Tr_VD57C_Hist");
        listTable.add("Tr_VD58_Hist");
        listTable.add("Tr_VD59_Hist");
        listTable.add("Tr_VD510A_Hist");
        listTable.add("Tr_VD510B_Hist");
        listTable.add("Tr_VD510C_Hist");
        listTable.add("Tr_VD510D_Hist");
        listTable.add("Tr_VD510E_Hist");
        listTable.add("Tr_VD510F_Hist");
        listTable.add("Tr_VD510G_Hist");
        listTable.add("Tr_VD510H_Hist");
        listTable.add("Tr_VD510I_Hist");

        listTable.add("Tr_VD51_KPEI");
        listTable.add("Tr_VD52_KPEI");
        listTable.add("Tr_VD53_KPEI");
        listTable.add("Tr_VD54_KPEI");
        listTable.add("Tr_VD55_KPEI");
        listTable.add("Tr_VD56A_KPEI");
        listTable.add("Tr_VD56B_KPEI");
        listTable.add("Tr_VD56C_KPEI");
        listTable.add("Tr_VD57A_KPEI");
        listTable.add("Tr_VD57B_KPEI");
        listTable.add("Tr_VD57C_KPEI");
        listTable.add("Tr_VD58_KPEI");
        listTable.add("Tr_VD59_KPEI");
        listTable.add("Tr_VD510A_KPEI");
        listTable.add("Tr_VD510B_KPEI");
        listTable.add("Tr_VD510C_KPEI");
        listTable.add("Tr_VD510D_KPEI");
        listTable.add("Tr_VD510E_KPEI");
        listTable.add("Tr_VD510F_KPEI");
        listTable.add("Tr_VD510G_KPEI");
        listTable.add("Tr_VD510H_KPEI");
        listTable.add("Tr_VD510I_KPEI");

        listTable.add("Tr_VD51_KPEI_Hist");
        listTable.add("Tr_VD52_KPEI_Hist");
        listTable.add("Tr_VD53_KPEI_Hist");
        listTable.add("Tr_VD54_KPEI_Hist");
        listTable.add("Tr_VD55_KPEI_Hist");
        listTable.add("Tr_VD56A_KPEI_Hist");
        listTable.add("Tr_VD56B_KPEI_Hist");
        listTable.add("Tr_VD56C_KPEI_Hist");
        listTable.add("Tr_VD57A_KPEI_Hist");
        listTable.add("Tr_VD57B_KPEI_Hist");
        listTable.add("Tr_VD57C_KPEI_Hist");
        listTable.add("Tr_VD58_KPEI_Hist");
        listTable.add("Tr_VD59_KPEI_Hist");
        listTable.add("Tr_VD510A_KPEI_Hist");
        listTable.add("Tr_VD510B_KPEI_Hist");
        listTable.add("Tr_VD510C_KPEI_Hist");
        listTable.add("Tr_VD510D_KPEI_Hist");
        listTable.add("Tr_VD510E_KPEI_Hist");
        listTable.add("Tr_VD510F_KPEI_Hist");
        listTable.add("Tr_VD510G_KPEI_Hist");
        listTable.add("Tr_VD510H_KPEI_Hist");
        listTable.add("Tr_VD510I_KPEI_Hist");

        return listTable;
    }
}
