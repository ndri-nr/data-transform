package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MkbTransformDto {
    private String username;
    private String userId;
    private String filename;
    private String kodePe;
    private Integer tahun;
    private Integer bulan;
    private Integer tanggal;
    private String managerName;
    private List<Vd51Dto> vd51 = new ArrayList<>();
    private List<Vd52Dto> vd52 = new ArrayList<>();
    private List<Vd53Dto> vd53 = new ArrayList<>();
    private List<Vd54Dto> vd54 = new ArrayList<>();
    private List<Vd55Dto> vd55 = new ArrayList<>();
    private List<Vd56aDto> vd56a = new ArrayList<>();
    private List<Vd56bDto> vd56b = new ArrayList<>();
    private List<Vd56cDto> vd56c = new ArrayList<>();
    private List<Vd57aDto> vd57a = new ArrayList<>();
    private List<Vd57bDto> vd57b = new ArrayList<>();
    private List<Vd57cDto> vd57c = new ArrayList<>();
    private List<Vd58Dto> vd58 = new ArrayList<>();
    private List<Vd59Dto> vd59 = new ArrayList<>();
    private List<Vd510aDto> vd510a = new ArrayList<>();
    private List<Vd510bDto> vd510b = new ArrayList<>();
    private List<Vd510cDto> vd510c = new ArrayList<>();
    private List<Vd510dDto> vd510d = new ArrayList<>();
    private List<Vd510eDto> vd510e = new ArrayList<>();
    private List<Vd510fDto> vd510f = new ArrayList<>();
    private List<Vd510gDto> vd510g = new ArrayList<>();
    private List<Vd510hDto> vd510h = new ArrayList<>();
    private List<Vd510iDto> vd510i = new ArrayList<>();
    private List<LogValidationDto> logValidation = new ArrayList<>();

    public MkbTransformDto(String username,
                           String userId,
                           String filename,
                           String kodePe,
                           int tahun,
                           int bulan,
                           int tanggal,
                           String managerName) {
        this.username = username;
        this.userId = userId;
        this.filename = filename;
        this.kodePe = kodePe;
        this.tahun = tahun;
        this.bulan = bulan;
        this.tanggal = tanggal;
        this.managerName = managerName;
    }

    public void constructVd5Dto(File file, Map<String, String> key) {
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(file));

            String line;
            int counter = 1;
            while ((line = reader.readLine()) != null) {
                processLine(line, key);
                counter++;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        if (items[0].startsWith("Tanggal")) {
            this.tahun = Integer.parseInt(items[1].substring(0, 4));
            this.bulan = Integer.parseInt(items[1].substring(4, 6));
            this.tanggal = Integer.parseInt(items[1].substring(6, 8));
        } else if (items[0].startsWith("Direktur")) {
            this.managerName = items[1];
        } else if (items[0].startsWith("VD51.")) {
            processVd51(line, key);
        }
        else if (items[0].startsWith("VD52.")) {
            processVd52(line, key);
        }
        else if (items[0].startsWith("VD53.")) {
            processVd53(line, key);
        }
        else if (items[0].startsWith("VD54.")) {
            processVd54(line, key);
        }
        else if (items[0].startsWith("VD55.")) {
            processVd55(line, key);
        }
        else if (items[0].startsWith("VD56.")) {
            processVd56(line, key);
        }
        else if (items[0].startsWith("VD57.")) {
            processVd57(line, key);
        }
        else if (items[0].startsWith("VD58.")) {
            processVd58(line, key);
        }
        else if (items[0].startsWith("VD59.")) {
            processVd59(line, key);
        }
        else if (items[0].startsWith("VD510.A")) {
            processVd510A(line, key);
        }
        else if (items[0].startsWith("VD510.B")) {
            processVd510B(line, key);
        }
        else if (items[0].startsWith("VD510.C")) {
            processVd510C(line, key);
        }
        else if (items[0].startsWith("VD510.D")) {
            processVd510D(line, key);
        }
        else if (items[0].startsWith("VD510.E")) {
            processVd510E(line, key);
        }
        else if (items[0].startsWith("VD510.F")) {
            processVd510F(line, key);
        }
        else if (items[0].startsWith("VD510.G")) {
            processVd510G(line, key);
        }
        else if (items[0].startsWith("VD510.H")) {
            processVd510H(line, key);
        }
        else if (items[0].startsWith("VD510.I")) {
            processVd510I(line, key);
        }
    }

    private void processVd51(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if (!exist) return;

        Vd51Dto dto = new Vd51Dto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.userId);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setSaldo(normalizeBigDecimalValue(items[1]));
        }

        this.vd51.add(dto);
    }

    private void processVd52(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if (!exist) return;

        Vd52Dto dto = new Vd52Dto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.userId);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setSaldo(normalizeBigDecimalValue(items[1]));
        }

        this.vd52.add(dto);
    }

    private void processVd53(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if (!exist) return;

        Vd53Dto dto = new Vd53Dto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.userId);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setNilaiDitambahkan(normalizeBigDecimalValue(items[1]));
        }

        this.vd53.add(dto);
    }

    private void processVd54(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if (!exist) return;

        Vd54Dto dto = new Vd54Dto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.userId);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setKodeJenisReksadana(items[1]);
        }
        if (items.length > 2 && !items[2].trim().isEmpty()) {
            dto.setNamaJenisReksadana(items[2]);
        }
        if (items.length > 3 && !items[3].trim().isEmpty()) {
            dto.setAfiliasi(items[3].equalsIgnoreCase("afiliasi"));
        }
        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setNilaiAktivaBersihUnit(normalizeBigDecimalValue(items[4]));
        }
        if (items.length > 5 && !items[5].trim().isEmpty()) {
            dto.setNilaiAktivaBersihReksadana(normalizeBigDecimalValue(items[5]));
        }
        if (items.length > 7 && !items[7].trim().isEmpty()) {
            dto.setBatasanMkbd(normalizeBigDecimalValue(items[7]));
        }
        if (items.length > 8 && !items[8].trim().isEmpty()) {
            dto.setKelebihanMkbd(normalizeBigDecimalValue(items[8]));
        }

        this.vd54.add(dto);
    }

    private void processVd55(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if (!exist) return;

        Vd55Dto dto = new Vd55Dto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.userId);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setKodeEfek(items[1]);
        }
        if (items.length > 2 && !items[2].trim().isEmpty()) {
            dto.setNilaiEfek(normalizeBigDecimalValue(items[2]));
        }
        if (items.length > 3 && !items[3].trim().isEmpty()) {
            dto.setKodeEfekLn(items[3]);
        }
        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setNilaiEfekLn(normalizeBigDecimalValue(items[4]));
        }
        if (items.length > 5 && !items[5].trim().isEmpty()) {
            dto.setNilaiEfekTutupLn(normalizeBigDecimalValue(items[5]));
        }
        if (items.length > 6 && !items[6].trim().isEmpty()) {
            dto.setNilaiHaircutTutupLn(normalizeBigDecimalValue(items[6]));
        }
        if (items.length > 7 && !items[7].trim().isEmpty()) {
            dto.setNilaiHaircutLn(normalizeBigDecimalValue(items[7]));
        }
        if (items.length > 8 && !items[8].trim().isEmpty()) {
            dto.setJmlPengembalianHaircut(normalizeBigDecimalValue(items[8]));
        }

        this.vd55.add(dto);
    }

    private void processVd56(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        String sub = "";
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                sub = key.get(allKey);
                break;
            }
        }

        if (!exist) {
            return;
        } else if (sub.equalsIgnoreCase("VD56A")) {
            Vd56aDto dto = new Vd56aDto();
            dto.setId(UUID.randomUUID().toString());
            dto.setKodePe(this.kodePe);
            dto.setTanggal(this.tanggal);
            dto.setBulan(this.bulan);
            dto.setTahun(this.tahun);
            dto.setCreatedAt(LocalDateTime.now());
            dto.setCreatedBy(this.userId);
            dto.setKodeAkun(items[0]);

            if (items.length > 1 && !items[1].trim().isEmpty()) {
                dto.setSaldo(normalizeBigDecimalValue(items[1]));
            }
            if (items.length > 2 && !items[2].trim().isEmpty()) {
                dto.setTerafiliasi(normalizeBigDecimalValue(items[2]));
            }
            if (items.length > 3 && !items[3].trim().isEmpty()) {
                dto.setTidakTerafiliasi(normalizeBigDecimalValue(items[3]));
            }

            this.vd56a.add(dto);
        } else if (sub.equalsIgnoreCase("VD56B")) {
            Vd56bDto dto = new Vd56bDto();
            dto.setId(UUID.randomUUID().toString());
            dto.setKodePe(this.kodePe);
            dto.setTanggal(this.tanggal);
            dto.setBulan(this.bulan);
            dto.setTahun(this.tahun);
            dto.setCreatedAt(LocalDateTime.now());
            dto.setCreatedBy(this.userId);
            dto.setKodeAkun(items[0]);

            if (items.length > 1 && !items[1].trim().isEmpty()) {
                dto.setSaldo(normalizeBigDecimalValue(items[1]));
            }
            if (items.length > 2 && !items[2].trim().isEmpty()) {
                dto.setDimiliki(normalizeBigDecimalValue(items[2]));
            }
            if (items.length > 3 && !items[3].trim().isEmpty()) {
                dto.setDipisahkan(normalizeBigDecimalValue(items[3]));
            }
            if (items.length > 4 && !items[4].trim().isEmpty()) {
                dto.setTidakDipisahkan(normalizeBigDecimalValue(items[4]));
            }

            this.vd56b.add(dto);
        } else if (sub.equalsIgnoreCase("VD56C")) {
            Vd56cDto dto = new Vd56cDto();
            dto.setId(UUID.randomUUID().toString());
            dto.setKodePe(this.kodePe);
            dto.setTanggal(this.tanggal);
            dto.setBulan(this.bulan);
            dto.setTahun(this.tahun);
            dto.setCreatedAt(LocalDateTime.now());
            dto.setCreatedBy(this.userId);
            dto.setKodeAkun(items[0]);

            if (items[0].equalsIgnoreCase("VD56.P")) {
                if (items.length > 1 && !items[1].trim().isEmpty()) {
                    dto.setPenjelasan(items[1]);
                }
            } else {
                if (items.length > 1 && !items[1].trim().isEmpty()) {
                    dto.setKodeBank(items[1]);
                }
                if (items.length > 2 && !items[2].trim().isEmpty()) {
                    dto.setIsSendiriNasabah(!items[2].equals("N"));
                }
                if (items.length > 3 && !items[3].trim().isEmpty()) {
                    dto.setNoRekening(items[3].equalsIgnoreCase("NIHIL") ? "" : items[3]);
                }
                if (items.length > 4 && !items[4].trim().isEmpty()) {
                    dto.setKodeCurrency(items[4]);
                }
                if (items.length > 5 && !items[5].trim().isEmpty()) {
                    dto.setSaldo(normalizeBigDecimalValue(items[5]));
                }
                if (items.length > 6 && !items[6].trim().isEmpty()) {
                    dto.setSaldoRupiah(normalizeBigDecimalValue(items[6]));
                }
            }

            this.vd56c.add(dto);
        }
    }

    private void processVd57(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        String sub = "";
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                sub = key.get(allKey);
                break;
            }
        }

        if(!exist) return;

        if (sub.equalsIgnoreCase("VD57A")) {
            Vd57aDto dto = new Vd57aDto();
            dto.setId(UUID.randomUUID().toString());
            dto.setKodePe(this.kodePe);
            dto.setTanggal(this.tanggal);
            dto.setBulan(this.bulan);
            dto.setTahun(this.tahun);
            dto.setCreatedAt(LocalDateTime.now());
            dto.setCreatedBy(this.userId);
            dto.setKodeAkun(items[0]);

            if (items.length > 1 && !items[1].trim().isEmpty()) {
                dto.setSaldo(normalizeBigDecimalValue(items[1]));
            }
            if (items.length > 2 && !items[2].trim().isEmpty()) {
                dto.setTerafiliasi(normalizeBigDecimalValue(items[2]));
            }
            if (items.length > 3 && !items[3].trim().isEmpty()) {
                dto.setTidakTerafiliasi(normalizeBigDecimalValue(items[3]));
            }

            this.vd57a.add(dto);
        }
        else if (sub.equalsIgnoreCase("VD57B")) {
            Vd57bDto dto = new Vd57bDto();
            dto.setId(UUID.randomUUID().toString());
            dto.setKodePe(this.kodePe);
            dto.setTanggal(this.tanggal);
            dto.setBulan(this.bulan);
            dto.setTahun(this.tahun);
            dto.setCreatedAt(LocalDateTime.now());
            dto.setCreatedBy(this.userId);
            dto.setKodeAkun(items[0]);

            if (items.length > 1 && !items[1].trim().isEmpty()) {
                dto.setSaldo(normalizeBigDecimalValue(items[1]));
            }
            if (items.length > 2 && !items[2].trim().isEmpty()) {
                dto.setDimiliki(normalizeBigDecimalValue(items[2]));
            }
            if (items.length > 3 && !items[3].trim().isEmpty()) {
                dto.setDipisahkan(normalizeBigDecimalValue(items[3]));
            }
            if (items.length > 4 && !items[4].trim().isEmpty()) {
                dto.setTidakDipisahkan(normalizeBigDecimalValue(items[4]));
            }

            this.vd57b.add(dto);
        }
        else if (sub.equalsIgnoreCase("VD57C")) {
            Vd57cDto dto = new Vd57cDto();
            dto.setId(UUID.randomUUID().toString());
            dto.setKodePe(this.kodePe);
            dto.setTanggal(this.tanggal);
            dto.setBulan(this.bulan);
            dto.setTahun(this.tahun);
            dto.setCreatedAt(LocalDateTime.now());
            dto.setCreatedBy(this.userId);
            dto.setKodeAkun(items[0]);

            if (items[0].equalsIgnoreCase("VD57.P")) {
                if (items.length > 1 && !items[1].trim().isEmpty()) {
                    dto.setPenjelasan(items[1]);
                }
            } else {
                if (items.length > 1 && !items[1].trim().isEmpty()) {
                    dto.setSaldo(normalizeBigDecimalValue(items[1]));
                }
                if (items.length > 2 && !items[2].trim().isEmpty()) {
                    dto.setLmHrKerja(normalizeBigDecimalValue(items[2]));
                }
                if (items.length > 3 && !items[3].trim().isEmpty()) {
                    dto.setDimiliki(normalizeBigDecimalValue(items[3]));
                }
                if (items.length > 4 && !items[4].trim().isEmpty()) {
                    dto.setDipisahkan(normalizeBigDecimalValue(items[4]));
                }
            }

            this.vd57c.add(dto);
        }

    }

    private void processVd58(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if(!exist) return;

        Vd58Dto dto = new Vd58Dto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.userId);
        dto.setKodeAkun(items[0]);

        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setNilai(normalizeBigDecimalValue(items[4]));
        }

        this.vd58.add(dto);
    }

    private void processVd59(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if(!exist) return;

        Vd59Dto dto = new Vd59Dto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.userId);
        dto.setKodeAkun(items[0]);

        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setJumlah(normalizeBigDecimalValue(items[4]));
        }
        if (items.length > 6 && !items[6].trim().isEmpty()) {
            dto.setTotal(normalizeBigDecimalValue(items[6]));
        }

        this.vd59.add(dto);
    }

    private void processVd510A(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if(!exist) return;

        Vd510aDto dto = new Vd510aDto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.userId);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setKodeEfek(items[1]);
        }
        if (items.length > 2 && !items[2].trim().isEmpty()) {
            dto.setPembeli(items[2]);
        }
        if (items.length > 3 && !items[3].trim().isEmpty()) {
            dto.setTanggalPenjualan(convertStringToDate(items[3]));
        }
        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setTanggalPembelian(convertStringToDate(items[4]));
        }
        if (items.length > 5 && !items[5].trim().isEmpty()) {
            dto.setNilaiPenjualan(normalizeBigDecimalValue(items[5]));
        }
        if (items.length > 6 && !items[6].trim().isEmpty()) {
            dto.setNilaiPembelian(normalizeBigDecimalValue(items[6]));
        }
        if (items.length > 7 && !items[7].trim().isEmpty()) {
            dto.setKodeEfekKolateral(items[7]);
        }
        if (items.length > 8 && !items[8].trim().isEmpty()) {
            dto.setJumlahJaminan(normalizeBigDecimalValue(items[8]));
        }
        if (items.length > 9 && !items[9].trim().isEmpty()) {
            dto.setNilaiPasarWajar(normalizeBigDecimalValue(items[9]));
        }
        if (items.length > 10 && !items[10].trim().isEmpty()) {
            dto.setNilaiRankingLiabilitas(normalizeBigDecimalValue(items[10]));
        }

        this.vd510a.add(dto);
    }

    private void processVd510B(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if(!exist) return;

        Vd510bDto dto = new Vd510bDto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.userId);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setKodeEfek(items[1]);
        }
        if (items.length > 2 && !items[2].trim().isEmpty()) {
            dto.setPenjual(items[2]);
        }
        if (items.length > 3 && !items[3].trim().isEmpty()) {
            dto.setTanggalPembelian(convertStringToDate(items[3]));
        }
        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setTanggalPenjualan(convertStringToDate(items[4]));
        }
        if (items.length > 5 && !items[5].trim().isEmpty()) {
            dto.setNilaiPembelian(normalizeBigDecimalValue(items[5]));
        }
        if (items.length > 6 && !items[6].trim().isEmpty()) {
            dto.setNilaiPenjualan(normalizeBigDecimalValue(items[6]));
        }
        if (items.length > 7 && !items[7].trim().isEmpty()) {
            dto.setKodeEfekKolateral(items[7]);
        }
        if (items.length > 8 && !items[8].trim().isEmpty()) {
            dto.setJumlahJaminan(normalizeBigDecimalValue(items[8]));
        }
        if (items.length > 9 && !items[9].trim().isEmpty()) {
            dto.setNilaiPasarWajar(normalizeBigDecimalValue(items[9]));
        }
        if (items.length > 10 && !items[10].trim().isEmpty()) {
            dto.setNilaiRankingLiabilitas(normalizeBigDecimalValue(items[10]));
        }

        this.vd510b.add(dto);
    }

    private void processVd510C(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if(!exist) return;

        Vd510cDto dto = new Vd510cDto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.userId);
        dto.setKodeAkun(items[0]);

        if (items.length > 2 && !items[2].trim().isEmpty()) {
            dto.setKodeEfek(items[2]);
        }
        if (items.length > 3 && !items[3].trim().isEmpty()) {
            dto.setTerafiliasi(items[3]);
        }
        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setLembarNominal(normalizeBigDecimalValue(items[4]));
        }
        if (items.length > 5 && !items[5].trim().isEmpty()) {
            dto.setHargaPerolehan(normalizeBigDecimalValue(items[5]));
        }
        if (items.length > 6 && !items[6].trim().isEmpty()) {
            dto.setHargaPasarWajar(normalizeBigDecimalValue(items[6]));
        }
        if (items.length > 7 && !items[7].trim().isEmpty()) {
            dto.setNilaiPasarWajar(normalizeBigDecimalValue(items[7]));
        }
        if (items.length > 8 && !items[8].trim().isEmpty()) {
            dto.setGrupEmiten(items[8]);
        }
        if (items.length > 9 && !items[9].trim().isEmpty()) {
            dto.setPersentaseNilaiPasar(items[9]);
        }
        if (items.length > 10 && !items[10].trim().isEmpty()) {
            dto.setNilaiRankingLiabilitas(normalizeBigDecimalValue(items[10]));
        }

        this.vd510c.add(dto);
    }

    private void processVd510D(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if(!exist) return;

        Vd510dDto dto = new Vd510dDto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.userId);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setNamaNasabah(items[1]);
        }
        if (items.length > 2 && !items[2].trim().isEmpty()) {
            dto.setMarginSelling(items[2]);
        }
        if (items.length > 3 && !items[3].trim().isEmpty()) {
            dto.setNilaiPembiayaan(normalizeBigDecimalValue(items[3]));
        }
        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setNilaiJaminan(normalizeBigDecimalValue(items[4]));
        }
        if (items.length > 5 && !items[5].trim().isEmpty()) {
            dto.setRasioPembiayaan(normalizeBigDecimalValue(items[5]));
        }
        if (items.length > 6 && !items[6].trim().isEmpty()) {
            dto.setNilaiRankingLiabilitiesNasabah(normalizeBigDecimalValue(items[6]));
        }
        if (items.length > 7 && !items[7].trim().isEmpty()) {
            dto.setNilaiRankingLiabilitiesRasio(normalizeBigDecimalValue(items[7]));
        }

        this.vd510d.add(dto);
    }

    private void processVd510E(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if(!exist) return;

        Vd510eDto dto = new Vd510eDto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.userId);
        dto.setKodeAkun(items[0]);

        if (items.length > 2 && !items[2].trim().isEmpty()) {
            dto.setKodeEfek(items[2]);
        }
        if (items.length > 3 && !items[3].trim().isEmpty()) {
            dto.setVolume(normalizeBigDecimalValue(items[3]));
        }
        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setHarga(normalizeBigDecimalValue(items[4]));
        }
        if (items.length > 5 && !items[5].trim().isEmpty()) {
            dto.setNilaiPasarWajar(normalizeBigDecimalValue(items[5]));
        }

        this.vd510e.add(dto);
    }

    private void processVd510F(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if(!exist) return;

        Vd510fDto dto = new Vd510fDto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.userId);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setTanggalKontrak(convertStringToDate(items[1]));
        }
        if (items.length > 2 && !items[2].trim().isEmpty()) {
            dto.setJenisPenjaminan(items[2]);
        }
        if (items.length > 3 && !items[3].trim().isEmpty()) {
            dto.setPihakDijamin(items[3]);
        }
        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setStatuPenjaminan(items[4]);
        }
        if (items.length > 5 && !items[5].trim().isEmpty()) {
            dto.setNilaiKomitmenPenjaminan(normalizeBigDecimalValue(items[5]));
        }
        if (items.length > 6 && !items[6].trim().isEmpty()) {
            dto.setHaircutAtasEfek(normalizeBigDecimalValue(items[6]));
        }
        if (items.length > 7 && !items[7].trim().isEmpty()) {
            dto.setNilaiBelumTerserap(normalizeBigDecimalValue(items[7]));
        }
        if (items.length > 8 && !items[8].trim().isEmpty()) {
            dto.setNilaiBankGaransi(normalizeBigDecimalValue(items[8]));
        }
        if (items.length > 9 && !items[9].trim().isEmpty()) {
            dto.setNilaiRankingLiabilitas(normalizeBigDecimalValue(items[9]));
        }

        this.vd510f.add(dto);
    }

    private void processVd510G(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if(!exist) return;

        Vd510gDto dto = new Vd510gDto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.userId);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setTanggalKontrak(convertStringToDate(items[1]));
        }
        if (items.length > 2 && !items[2].trim().isEmpty()) {
            dto.setPihakDijamin(items[2]);
        }
        if (items.length > 3 && !items[3].trim().isEmpty()) {
            dto.setTerafiliasi(items[3]);
        }
        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setRincianPenjaminan(items[4]);
        }
        if (items.length > 5 && !items[5].trim().isEmpty()) {
            dto.setJangkaWaktuPnejaminan(items[5]);
        }
        if (items.length > 6 && !items[6].trim().isEmpty()) {
            dto.setTanggalBerakhir(convertStringToDate(items[6]));
        }
        if (items.length > 7 && !items[7].trim().isEmpty()) {
            dto.setNilaiPenjaminan(normalizeBigDecimalValue(items[7]));
        }
        if (items.length > 8 && !items[8].trim().isEmpty()) {
            dto.setNilaiRankingLiabilitas(normalizeBigDecimalValue(items[8]));
        }

        this.vd510g.add(dto);
    }

    private void processVd510H(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if(!exist) return;

        Vd510hDto dto = new Vd510hDto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.getUserId());
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setTanggalKomitmen(convertStringToDate(items[1]));
        }
        if (items.length > 2 && !items[2].trim().isEmpty()) {
            dto.setRincianBelanja(items[2]);
        }
        if (items.length > 3 && !items[3].trim().isEmpty()) {
            dto.setTanggalRealisasi(convertStringToDate(items[3]));
        }
        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setKomitmenTerealisasi(normalizeBigDecimalValue(items[4]));
        }
        if (items.length > 5 && !items[5].trim().isEmpty()) {
            dto.setKomitmenBelumTerealisasi(normalizeBigDecimalValue(items[4]));
        }
        if (items.length > 6 && !items[6].trim().isEmpty()) {
            dto.setNilaiRankingLiabilitas(normalizeBigDecimalValue(items[4]));
        }

        this.vd510h.add(dto);
    }

    private void processVd510I(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysPossibilities(items[0]);
        boolean exist = false;
        for (String allKey : allKeys) {
            if (key.containsKey(allKey)) {
                exist = true;
                break;
            }
        }

        if(!exist) return;

        Vd510iDto dto = new Vd510iDto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.userId);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setJenisTransaksi(items[2]);
        }
        if (items.length > 2 && !items[2].trim().isEmpty()) {
            dto.setTanggalTransaksi(convertStringToDate(items[1]));
        }
        if (items.length > 3 && !items[3].trim().isEmpty()) {
            dto.setJenisMataUang(items[3]);
        }
        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setNilaiTransaksi(normalizeBigDecimalValue(items[4]));
        }
        if (items.length > 5 && !items[5].trim().isEmpty()) {
            dto.setUntungRugiBelumTerealisasi(normalizeBigDecimalValue(items[4]));
        }
        if (items.length > 6 && !items[6].trim().isEmpty()) {
            dto.setNilaiRankingLiabilitas(normalizeBigDecimalValue(items[4]));
        }

        this.vd510i.add(dto);
    }

    private Float normalizeFloatValue(String value) {
        value = value.replaceAll(",", ".");
        return Float.parseFloat(value);
    }

    private BigDecimal normalizeBigDecimalValue(String value) {
        value = value.replaceAll(",", ".");
        return new BigDecimal(value);
    }

    private List<String> getAllKeysPossibilities(String kodeAkun) {
        String[] kodeAkunArr = kodeAkun.split("\\.");
        List<String> keys = new ArrayList<>();

        String baseKey = kodeAkunArr[0] + "." + kodeAkunArr[1];
        keys.add(baseKey);
        keys.add(baseKey + ".");
        for (int i = 2; i < kodeAkunArr.length; i++) {
            baseKey = baseKey + "." + kodeAkunArr[i];
            keys.add(baseKey);
            keys.add(baseKey + ".");
        }

        return keys;
    }

    private Date convertStringToDate(String dateStr) {
        Date sqlDate = null;

        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsed = format.parse(dateStr);
            sqlDate = new Date(parsed.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sqlDate;
    }

    public void validateContent(Connection conn) {
        String query = "select kode, sub, requirements from vw_mapping_kode_akun";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(query);
            ResultSet list = stmt.executeQuery();

            while (list.next()) {
                String kode = list.getString("kode");
                String group = list.getString("sub");
                String[] requirements = list.getString("requirements").split("\\|");

                processContentValidation(kode, group, requirements);
            }

            insertDataLogValidation(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void processContentValidation(String kode, String group, String[] requirements) {
        switch (group) {
            case "VD51":
                validateVD51(kode, requirements);
                break;
            case "VD52":
                validateVD52(kode, requirements);
                break;
            case "VD53":
                validateVD53(kode, requirements);
                break;
            case "VD54":
                validateVD54(kode, requirements);
                break;
            case "VD55":
                validateVD55(kode, requirements);
                break;
            case "VD56A":
                validateVD56A(kode, requirements);
                break;
            case "VD56B":
                validateVD56B(kode, requirements);
                break;
            case "VD56C":
                validateVD56C(kode, requirements);
                break;
            case "VD57A":
                validateVD57A(kode, requirements);
                break;
            case "VD57B":
                validateVD57B(kode, requirements);
                break;
            case "VD57C":
                validateVD57C(kode, requirements);
                break;
            case "VD58":
                validateVD58(kode, requirements);
                break;
            case "VD59":
                validateVD59(kode, requirements);
                break;
            case "VD510A":
                validateVD510A(kode, requirements);
                break;
            case "VD510B":
                validateVD510B(kode, requirements);
                break;
            case "VD510C":
                validateVD510C(kode, requirements);
                break;
            case "VD510D":
                validateVD510D(kode, requirements);
                break;
            case "VD510E":
                validateVD510E(kode, requirements);
                break;
            case "VD510F":
                validateVD510F(kode, requirements);
                break;
            case "VD510G":
                validateVD510G(kode, requirements);
                break;
            case "VD510H":
                validateVD510H(kode, requirements);
                break;
            case "VD510I":
                validateVD510I(kode, requirements);
                break;
            default:
                break;
        }
    }

    private void insertDataLogValidation(Connection conn)
            throws SQLException {
        if (!this.logValidation.isEmpty()) {
            String insertQuery = "INSERT INTO public.\"LogValidation\" " +
                    "(\"KodePe\", \"KodeAkun\", \"FileName\", \"DetailDescription\", " +
                    "\"MessageDescription\", \"CreatedAt\", \"CreatedBy\") " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(insertQuery);

            for (LogValidationDto temp : this.logValidation) {
                stmt.setString(1, temp.getKodePe());
                stmt.setString(2, temp.getKodeAkun());
                stmt.setString(3, temp.getFileName());
                stmt.setString(4, temp.getDetailDescription());
                stmt.setString(5, temp.getMessageDescription());
                stmt.setTimestamp(6, Timestamp.valueOf(temp.getCreatedAt()));
                stmt.setString(7, temp.getCreatedBy());
                stmt.addBatch();
            }

            stmt.executeBatch();
        }
    }

    private void validateVD51(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd51Dto temp : this.vd51) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getSaldo() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getSaldo() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD52(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd52Dto temp : this.vd52) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getSaldo() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getSaldo() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD53(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd53Dto temp : this.vd53) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getNilaiDitambahkan() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiDitambahkan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getNilaiDitambahkan() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiDitambahkan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD54(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd54Dto temp : this.vd54) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getNilaiAktivaBersihUnit() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiAktivaBersihUnit untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiAktivaBersihReksadana() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiAktivaBersihReksadana untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getBatasanMkbd() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space BatasanMkbd untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getKelebihanMkbd() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space KelebihanMkbd untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getNilaiAktivaBersihUnit() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiAktivaBersihUnit untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiAktivaBersihReksadana() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiAktivaBersihReksadana untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getBatasanMkbd() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space BatasanMkbd untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getKelebihanMkbd() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space KelebihanMkbd untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD55(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd55Dto temp : this.vd55) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getNilaiEfek() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiEfek untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiEfekLn() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiEfekLN untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiEfekTutupLn() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space NilaiEfekTutupLN untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiHaircutTutupLn() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space NilaiHaircutTutupLN untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiHaircutLn() == null && requirements[4].equals("t")) {
                    addLogValidation(akun, "Space NilaiHaircutLN untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getJmlPengembalianHaircut() == null && requirements[5].equals("t")) {
                    addLogValidation(akun, "Space JmlPengembalianHaircut untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getNilaiEfek() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiEfek untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiEfekLn() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiEfekLN untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiEfekTutupLn() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space NilaiEfekTutupLN untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiHaircutTutupLn() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space NilaiHaircutTutupLN untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiHaircutLn() == null && requirements[4].equals("t")) {
                    addLogValidation(akun, "Space NilaiHaircutLN untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getJmlPengembalianHaircut() == null && requirements[5].equals("t")) {
                    addLogValidation(akun, "Space JmlPengembalianHaircut untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD56A(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd56aDto temp : this.vd56a) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getSaldo() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getTerafiliasi() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space Terafiliasi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getTidakTerafiliasi() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space TidakTerafiliasi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getSaldo() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getTerafiliasi() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space Terafiliasi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getTidakTerafiliasi() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space TidakTerafiliasi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD56B(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd56bDto temp : this.vd56b) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getSaldo() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getDimiliki() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space Dimiliki untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getDipisahkan() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space Dipisahkan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getTidakDipisahkan() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space TidakDipisahkan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getSaldo() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getDimiliki() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space Dimiliki untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getDipisahkan() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space Dipisahkan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getTidakDipisahkan() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space TidakDipisahkan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD56C(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd56cDto temp : this.vd56c) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getIsSendiriNasabah() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space IsSendiriNasabah untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNoRekening() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NoRekening untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getKodeCurrency() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space KodeCurrency untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getSaldo() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getSaldoRupiah() == null && requirements[4].equals("t")) {
                    addLogValidation(akun, "Space SaldoRupiah untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getIsSendiriNasabah() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space IsSendiriNasabah untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNoRekening() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NoRekening untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getKodeCurrency() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space KodeCurrency untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getSaldo() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getSaldoRupiah() == null && requirements[4].equals("t")) {
                    addLogValidation(akun, "Space SaldoRupiah untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD57A(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd57aDto temp : this.vd57a) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getSaldo() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getTerafiliasi() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space Terafiliasi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getTidakTerafiliasi() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space TidakTerafiliasi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getSaldo() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getTerafiliasi() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space Terafiliasi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getTidakTerafiliasi() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space TidakTerafiliasi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD57B(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd57bDto temp : this.vd57b) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getSaldo() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getDimiliki() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space Dimiliki untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getDipisahkan() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space Dipisahkan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getTidakDipisahkan() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space TidakDipisahkan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getSaldo() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getDimiliki() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space Dimiliki untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getDipisahkan() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space Dipisahkan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getTidakDipisahkan() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space TidakDipisahkan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD57C(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd57cDto temp : this.vd57c) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getSaldo() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getLmHrKerja() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space LmHrKerja untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getDimiliki() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space Dimiliki untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getDipisahkan() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space Dipisahkan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getSaldo() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Saldo untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getLmHrKerja() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space LmHrKerja untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getDimiliki() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space Dimiliki untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getDipisahkan() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space Dipisahkan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD58(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd58Dto temp : this.vd58) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getNilai() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Nilai untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getNilai() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Nilai untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD59(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd59Dto temp : this.vd59) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getJumlah() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Jumlah untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getTotal() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Total untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getJumlah() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Jumlah untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getTotal() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space Total untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD510A(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd510aDto temp : this.vd510a) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getNilaiPenjualan() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiPenjualan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiPembelian() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiPembelianKembali untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiPasarWajar() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space NilaiPasarWajar untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitas() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilities untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getNilaiPenjualan() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiPenjualan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiPembelian() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiPembelianKembali untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiPasarWajar() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space NilaiPasarWajar untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitas() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilities untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD510B(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd510bDto temp : this.vd510b) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getNilaiPembelian() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiPembelian untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiPenjualan() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiPenjualanKembali untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiPasarWajar() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space NilaiPasarWajar untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitas() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilities untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getNilaiPembelian() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiPembelian untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiPenjualan() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiPenjualanKembali untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiPasarWajar() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space NilaiPasarWajar untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitas() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilities untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD510C(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd510cDto temp : this.vd510c) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getNilaiPasarWajar() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiPasarWajar untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitas() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilities untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getNilaiPasarWajar() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiPasarWajar untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitas() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilities untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD510D(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd510dDto temp : this.vd510d) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getMarginSelling() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiPembiayaanMarjin untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiPembiayaan() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiJaminanPembiayaan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitiesNasabah() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilitiesNasabah untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitiesRasio() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilitiesKelebihan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getMarginSelling() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiPembiayaanMarjin untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiPembiayaan() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiJaminanPembiayaan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitiesNasabah() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilitiesNasabah untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitiesRasio() == null && requirements[3].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilitiesKelebihan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD510E(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd510eDto temp : this.vd510e) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getNilaiPasarWajar() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiPasarWajar untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getNilaiPasarWajar() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiPasarWajar untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD510F(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd510fDto temp : this.vd510f) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getNilaiKomitmenPenjaminan() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiPorsiKomitmenPenjaminan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitas() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilities untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getNilaiKomitmenPenjaminan() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiPorsiKomitmenPenjaminan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitas() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilities untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD510G(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd510gDto temp : this.vd510g) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getNilaiPenjaminan() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiPenjaminan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitas() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilities untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getNilaiPenjaminan() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiPenjaminan untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitas() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilities untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD510H(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd510hDto temp : this.vd510h) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getKomitmenTerealisasi() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiKomitmenTerealisasi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getKomitmenBelumTerealisasi() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiKomitmenBelumTerealisasi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitas() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilities untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getKomitmenTerealisasi() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiKomitmenTerealisasi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getKomitmenBelumTerealisasi() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiKomitmenBelumTerealisasi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitas() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilities untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void validateVD510I(String kode, String[] requirements) {
        boolean isWildcard = kode.charAt(kode.length() - 1) == '.';
        for (Vd510iDto temp : this.vd510i) {
            String akun = temp.getKodeAkun();
            if (!isWildcard && akun.equals(kode)) {
                if (temp.getNilaiTransaksi() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiTransaksi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getUntungRugiBelumTerealisasi() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiUntungRugi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitas() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilities untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            } else if (akun.contains(kode)) {
                if (temp.getNilaiTransaksi() == null && requirements[0].equals("t")) {
                    addLogValidation(akun, "Space NilaiTransaksi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getUntungRugiBelumTerealisasi() == null && requirements[1].equals("t")) {
                    addLogValidation(akun, "Space NilaiUntungRugi untuk akun " + akun + " dalam file required dan tidak terisi");
                }
                if (temp.getNilaiRankingLiabilitas() == null && requirements[2].equals("t")) {
                    addLogValidation(akun, "Space NilaiRankingLiabilities untuk akun " + akun + " dalam file required dan tidak terisi");
                }
            }
        }
    }

    private void addLogValidation(String akun, String message) {
        this.logValidation.add(LogValidationDto.builder()
                .kodeAkun(akun)
                .kodePe(this.kodePe)
                .fileName(this.filename)
                .detailDescription("EMPTYSPACE")
                .messageDescription(message)
                .createdAt(LocalDateTime.now())
                .createdBy(this.userId)
                .build());
    }
}
