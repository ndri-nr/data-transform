package com.kpei.mkbd.datatransform.dto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MkbTransformDto {
    private String username;
    private String filename;
    private String kodePe;
    private int tahun;
    private int bulan;
    private int tanggal;
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

    public MkbTransformDto() {
    }

    public MkbTransformDto(String username,
                           String filename,
                           String kodePe,
                           int tahun,
                           int bulan,
                           int tanggal,
                           String managerName) {
        this.username = username;
        this.filename = filename;
        this.kodePe = kodePe;
        this.tahun = tahun;
        this.bulan = bulan;
        this.tanggal = tanggal;
        this.managerName = managerName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getKodePe() {
        return kodePe;
    }

    public void setKodePe(String kodePe) {
        this.kodePe = kodePe;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public int getTanggal() {
        return tanggal;
    }

    public void setTanggal(int tanggal) {
        this.tanggal = tanggal;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public List<Vd51Dto> getVd51() {
        return vd51;
    }

    public void setVd51(List<Vd51Dto> vd51) {
        this.vd51 = vd51;
    }

    public List<Vd52Dto> getVd52() {
        return vd52;
    }

    public void setVd52(List<Vd52Dto> vd52) {
        this.vd52 = vd52;
    }

    public List<Vd53Dto> getVd53() {
        return vd53;
    }

    public void setVd53(List<Vd53Dto> vd53) {
        this.vd53 = vd53;
    }

    public List<Vd54Dto> getVd54() {
        return vd54;
    }

    public void setVd54(List<Vd54Dto> vd54) {
        this.vd54 = vd54;
    }

    public List<Vd55Dto> getVd55() {
        return vd55;
    }

    public void setVd55(List<Vd55Dto> vd55) {
        this.vd55 = vd55;
    }

    public List<Vd56aDto> getVd56a() {
        return vd56a;
    }

    public void setVd56a(List<Vd56aDto> vd56a) {
        this.vd56a = vd56a;
    }

    public List<Vd56bDto> getVd56b() {
        return vd56b;
    }

    public void setVd56b(List<Vd56bDto> vd56b) {
        this.vd56b = vd56b;
    }

    public List<Vd56cDto> getVd56c() {
        return vd56c;
    }

    public void setVd56c(List<Vd56cDto> vd56c) {
        this.vd56c = vd56c;
    }

    public List<Vd57aDto> getVd57a() {
        return vd57a;
    }

    public void setVd57a(List<Vd57aDto> vd57a) {
        this.vd57a = vd57a;
    }

    public List<Vd57bDto> getVd57b() {
        return vd57b;
    }

    public void setVd57b(List<Vd57bDto> vd57b) {
        this.vd57b = vd57b;
    }

    public List<Vd57cDto> getVd57c() {
        return vd57c;
    }

    public void setVd57c(List<Vd57cDto> vd57c) {
        this.vd57c = vd57c;
    }

    public List<Vd58Dto> getVd58() {
        return vd58;
    }

    public void setVd58(List<Vd58Dto> vd58) {
        this.vd58 = vd58;
    }

    public List<Vd59Dto> getVd59() {
        return vd59;
    }

    public void setVd59(List<Vd59Dto> vd59) {
        this.vd59 = vd59;
    }

    public List<Vd510aDto> getVd510a() {
        return vd510a;
    }

    public void setVd510a(List<Vd510aDto> vd510a) {
        this.vd510a = vd510a;
    }

    public List<Vd510bDto> getVd510b() {
        return vd510b;
    }

    public void setVd510b(List<Vd510bDto> vd510b) {
        this.vd510b = vd510b;
    }

    public List<Vd510cDto> getVd510c() {
        return vd510c;
    }

    public void setVd510c(List<Vd510cDto> vd510c) {
        this.vd510c = vd510c;
    }

    public List<Vd510dDto> getVd510d() {
        return vd510d;
    }

    public void setVd510d(List<Vd510dDto> vd510d) {
        this.vd510d = vd510d;
    }

    public List<Vd510eDto> getVd510e() {
        return vd510e;
    }

    public void setVd510e(List<Vd510eDto> vd510e) {
        this.vd510e = vd510e;
    }

    public List<Vd510fDto> getVd510f() {
        return vd510f;
    }

    public void setVd510f(List<Vd510fDto> vd510f) {
        this.vd510f = vd510f;
    }

    public List<Vd510gDto> getVd510g() {
        return vd510g;
    }

    public void setVd510g(List<Vd510gDto> vd510g) {
        this.vd510g = vd510g;
    }

    public List<Vd510hDto> getVd510h() {
        return vd510h;
    }

    public void setVd510h(List<Vd510hDto> vd510h) {
        this.vd510h = vd510h;
    }

    public List<Vd510iDto> getVd510i() {
        return vd510i;
    }

    public void setVd510i(List<Vd510iDto> vd510i) {
        this.vd510i = vd510i;
    }

    public void constructVd5Dto(File file, Map<String, String> key) {
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(file));

            String line;
            int counter = 1;
            while ((line = reader.readLine()) != null) {
                if (counter > 3) {
                    processLine(line, key);
                }
                counter++;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("total vd51 " + vd51.size());
        System.out.println("total vd52 " + vd52.size());
        System.out.println("total vd53 " + vd53.size());
        System.out.println("total vd54 " + vd54.size());
        System.out.println("total vd55 " + vd55.size());
        System.out.println("total vd56a " + vd56a.size());
        System.out.println("total vd56b " + vd56b.size());
        System.out.println("total vd56c " + vd56c.size());
        System.out.println("total vd57a " + vd57a.size());
        System.out.println("total vd57b " + vd57b.size());
        System.out.println("total vd57c " + vd57c.size());
        System.out.println("total vd58 " + vd58.size());
        System.out.println("total vd59 " + vd59.size());
        System.out.println("total vd510A " + vd510a.size());
        System.out.println("total vd510B " + vd510b.size());
        System.out.println("total vd510C " + vd510c.size());
        System.out.println("total vd510D " + vd510d.size());
        System.out.println("total vd510E " + vd510e.size());
        System.out.println("total vd510F " + vd510f.size());
        System.out.println("total vd510G " + vd510g.size());
        System.out.println("total vd510H " + vd510h.size());
        System.out.println("total vd510I " + vd510i.size());
    }

    private void processLine(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        if (items[0].startsWith("VD51.")) {
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

        if (!key.containsKey(items[0])) {
            return;
        }

        Vd51Dto dto = new Vd51Dto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setSaldo(normalizeDoubleValue(items[1]));
        }

        this.vd51.add(dto);
    }

    private void processVd52(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        if (!key.containsKey(items[0])) {
            return;
        }

        Vd52Dto dto = new Vd52Dto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setSaldo(normalizeDoubleValue(items[1]));
        }

        this.vd52.add(dto);
    }

    private void processVd53(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        if (!key.containsKey(items[0])) {
            return;
        }

        Vd53Dto dto = new Vd53Dto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setNilaiDitambahkan(normalizeDoubleValue(items[1]));
        }

        this.vd53.add(dto);
    }

    private void processVd54(String line, Map<String, String> key) {
        String[] items = line.split("\\|");
        String kodeAkun = items[0];
        String[] kodeAkunArr = kodeAkun.split("\\.");
        String sub = "";
        if (!key.containsKey(items[0]) && kodeAkunArr.length == 2) {
            return;
        } else if (!key.containsKey(kodeAkunArr[0] + "." + kodeAkunArr[1]) &&
                !key.containsKey(kodeAkunArr[0] + "." + kodeAkunArr[1] + ".") &&
                !key.containsKey(kodeAkunArr[0] + "." + kodeAkunArr[1] + "." + kodeAkunArr[2]) &&
                kodeAkunArr.length == 3) {
            return;
        } else if (key.containsKey(kodeAkunArr[0] + "." + kodeAkunArr[1])) {
            sub = key.get(kodeAkunArr[0] + "." + kodeAkunArr[1]);
        } else if (key.containsKey(kodeAkunArr[0] + "." + kodeAkunArr[1] + ".")) {
            sub = key.get(kodeAkunArr[0] + "." + kodeAkunArr[1] + ".");
        } else if (key.containsKey(kodeAkunArr[0] + "." + kodeAkunArr[1] + "." + kodeAkunArr[2])) {
            sub = key.get(kodeAkunArr[0] + "." + kodeAkunArr[1] + "." + kodeAkunArr[2]);
        } else {
            sub = key.get(items[0]);
        }

        if (sub.isEmpty()) {
            return;
        }

        Vd54Dto dto = new Vd54Dto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setKodeJenisReksadana(items[1]);
        }
        if (items.length > 2 && !items[2].trim().isEmpty()) {
            dto.setNamaReksadana(items[2]);
        }
        if (items.length > 3 && !items[3].trim().isEmpty()) {
            dto.setAfiliasi(items[3].equalsIgnoreCase("afiliasi"));
        }
        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setNilaiAktivaBersihUnit(normalizeDoubleValue(items[4]));
        }
        if (items.length > 5 && !items[5].trim().isEmpty()) {
            dto.setNilaiAktivaBersihReksadana(normalizeDoubleValue(items[5]));
        }
        if (items.length > 7 && !items[7].trim().isEmpty()) {
            dto.setBatasaMkbd(normalizeDoubleValue(items[7]));
        }
        if (items.length > 8 && !items[8].trim().isEmpty()) {
            dto.setKelebihanBatasan(normalizeDoubleValue(items[8]));
        }

        this.vd54.add(dto);
    }

    private void processVd55(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        if (!key.containsKey(items[0])) {
            return;
        }

        Vd55Dto dto = new Vd55Dto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setKodeEfek(items[1]);
        }
        if (items.length > 2 && !items[2].trim().isEmpty()) {
            dto.setNilaiEfek(normalizeDoubleValue(items[2]));
        }
        if (items.length > 3 && !items[3].trim().isEmpty()) {
            dto.setKodeEfekLN(items[3]);
        }
        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setNilaiEfekLN(normalizeDoubleValue(items[4]));
        }
        if (items.length > 5 && !items[5].trim().isEmpty()) {
            dto.setNilaiEfekTutupLN(normalizeDoubleValue(items[5]));
        }
        if (items.length > 6 && !items[6].trim().isEmpty()) {
            dto.setNilaiHaircutTutupLN(normalizeDoubleValue(items[6]));
        }
        if (items.length > 7 && !items[7].trim().isEmpty()) {
            dto.setNilaiHaircutLN(normalizeDoubleValue(items[7]));
        }
        if (items.length > 8 && !items[8].trim().isEmpty()) {
            dto.setJmlPengembalianHaircut(normalizeDoubleValue(items[8]));
        }

        this.vd55.add(dto);
    }

    private void processVd56(String line, Map<String, String> key) {
        String[] items = line.split("\\|");
        String kodeAkun = items[0];
        String[] kodeAkunArr = kodeAkun.split("\\.");
        String sub = "";
        if (!key.containsKey(items[0]) && kodeAkunArr.length == 2) {
            return;
        } else if (!key.containsKey(kodeAkunArr[0] + "." + kodeAkunArr[1]) &&
                !key.containsKey(kodeAkunArr[0] + "." + kodeAkunArr[1] + ".") &&
                !key.containsKey(kodeAkunArr[0] + "." + kodeAkunArr[1] + "." + kodeAkunArr[2]) &&
                kodeAkunArr.length == 3) {
            return;
        } else if (key.containsKey(kodeAkunArr[0] + "." + kodeAkunArr[1])) {
            sub = key.get(kodeAkunArr[0] + "." + kodeAkunArr[1]);
        } else if (key.containsKey(kodeAkunArr[0] + "." + kodeAkunArr[1] + ".")) {
            sub = key.get(kodeAkunArr[0] + "." + kodeAkunArr[1] + ".");
        } else if (key.containsKey(kodeAkunArr[0] + "." + kodeAkunArr[1] + "." + kodeAkunArr[2])) {
            sub = key.get(kodeAkunArr[0] + "." + kodeAkunArr[1] + "." + kodeAkunArr[2]);
        } else {
            sub = key.get(items[0]);
        }

        if (sub.isEmpty()) {
            return;
        } else if (sub.equalsIgnoreCase("VD56A")) {
            Vd56aDto dto = new Vd56aDto();
            dto.setId(UUID.randomUUID().toString());
            dto.setKodePe(this.kodePe);
            dto.setTanggal(this.tanggal);
            dto.setBulan(this.bulan);
            dto.setTahun(this.tahun);
            dto.setCreatedAt(LocalDateTime.now());
            dto.setCreatedBy(this.username);
            dto.setKodeAkun(items[0]);

            if (items.length > 1 && !items[1].trim().isEmpty()) {
                dto.setSaldo(normalizeDoubleValue(items[1]));
            }
            if (items.length > 2 && !items[2].trim().isEmpty()) {
                dto.setTerafiliasi(normalizeDoubleValue(items[2]));
            }
            if (items.length > 3 && !items[3].trim().isEmpty()) {
                dto.setTidakTerafiliasi(normalizeDoubleValue(items[3]));
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
            dto.setCreatedBy(this.username);
            dto.setKodeAkun(items[0]);

            if (items.length > 1 && !items[1].trim().isEmpty()) {
                dto.setSaldo(normalizeDoubleValue(items[1]));
            }
            if (items.length > 2 && !items[2].trim().isEmpty()) {
                dto.setDimiliki(normalizeDoubleValue(items[2]));
            }
            if (items.length > 3 && !items[3].trim().isEmpty()) {
                dto.setDipisahkan(normalizeDoubleValue(items[3]));
            }
            if (items.length > 4 && !items[4].trim().isEmpty()) {
                dto.setTidakDipisahkan(normalizeDoubleValue(items[4]));
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
            dto.setCreatedBy(this.username);
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
                    dto.setSendiriNasabah(!items[2].equals("N"));
                }
                if (items.length > 3 && !items[3].trim().isEmpty()) {
                    dto.setNoRekening(items[3].equalsIgnoreCase("NIHIL") ? "" : items[3]);
                }
                if (items.length > 4 && !items[4].trim().isEmpty()) {
                    dto.setKodeCurrency(items[4]);
                }
                if (items.length > 5 && !items[5].trim().isEmpty()) {
                    dto.setSaldo(normalizeDoubleValue(items[5]));
                }
                if (items.length > 6 && !items[6].trim().isEmpty()) {
                    dto.setSaldoRupiah(normalizeDoubleValue(items[6]));
                }
            }

            this.vd56c.add(dto);
        }
    }

    private void processVd57(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        if (!key.containsKey(items[0])) {
            return;
        }

        String sub = key.get(items[0]);

        if (sub.equalsIgnoreCase("VD57A")) {
            Vd57aDto dto = new Vd57aDto();
            dto.setId(UUID.randomUUID().toString());
            dto.setKodePe(this.kodePe);
            dto.setTanggal(this.tanggal);
            dto.setBulan(this.bulan);
            dto.setTahun(this.tahun);
            dto.setCreatedAt(LocalDateTime.now());
            dto.setCreatedBy(this.username);
            dto.setKodeAkun(items[0]);

            if (items.length > 1 && !items[1].trim().isEmpty()) {
                dto.setSaldo(normalizeDoubleValue(items[1]));
            }
            if (items.length > 2 && !items[2].trim().isEmpty()) {
                dto.setTerafiliasi(normalizeDoubleValue(items[2]));
            }
            if (items.length > 3 && !items[3].trim().isEmpty()) {
                dto.setTidakTerafiliasi(normalizeDoubleValue(items[3]));
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
            dto.setCreatedBy(this.username);
            dto.setKodeAkun(items[0]);

            if (items.length > 1 && !items[1].trim().isEmpty()) {
                dto.setSaldo(normalizeDoubleValue(items[1]));
            }
            if (items.length > 2 && !items[2].trim().isEmpty()) {
                dto.setDimiliki(normalizeDoubleValue(items[2]));
            }
            if (items.length > 3 && !items[3].trim().isEmpty()) {
                dto.setDipisahkan(normalizeDoubleValue(items[3]));
            }
            if (items.length > 4 && !items[4].trim().isEmpty()) {
                dto.setTidakDipisahkan(normalizeDoubleValue(items[4]));
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
            dto.setCreatedBy(this.username);
            dto.setKodeAkun(items[0]);

            if (items[0].equalsIgnoreCase("VD57.P")) {
                if (items.length > 1 && !items[1].trim().isEmpty()) {
                    dto.setPenjelasan(items[1]);
                }
            } else {
                if (items.length > 1 && !items[1].trim().isEmpty()) {
                    dto.setSaldo(normalizeDoubleValue(items[1]));
                }
                if (items.length > 2 && !items[2].trim().isEmpty()) {
                    dto.setLmHrKerja(normalizeDoubleValue(items[2]));
                }
                if (items.length > 3 && !items[3].trim().isEmpty()) {
                    dto.setDimiliki(normalizeDoubleValue(items[3]));
                }
                if (items.length > 4 && !items[4].trim().isEmpty()) {
                    dto.setDipisahkan(normalizeDoubleValue(items[4]));
                }
            }

            this.vd57c.add(dto);
        }

    }

    private void processVd58(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        if (!key.containsKey(items[0])) {
            return;
        }

        Vd58Dto dto = new Vd58Dto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setNilai(normalizeDoubleValue(items[4]));
        }

        this.vd58.add(dto);
    }

    private void processVd59(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        if (!key.containsKey(items[0])) {
            return;
        }

        Vd59Dto dto = new Vd59Dto();
        dto.setId(UUID.randomUUID().toString());
        dto.setKodePe(this.kodePe);
        dto.setTanggal(this.tanggal);
        dto.setBulan(this.bulan);
        dto.setTahun(this.tahun);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setJumlah(normalizeDoubleValue(items[4]));
        }
        if (items.length > 6 && !items[6].trim().isEmpty()) {
            dto.setTotal(normalizeDoubleValue(items[6]));
        }

        this.vd59.add(dto);
    }

    private void processVd510A(String line, Map<String, String> key) {
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysVd510(items[0]);
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
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        if (items.length > 1 && !items[1].trim().isEmpty()) {
            dto.setKodeEfek(items[1]);
        }
        if (items.length > 2 && !items[2].trim().isEmpty()) {
            dto.setPembeli(items[2]);
        }
        if (items.length > 3 && !items[3].trim().isEmpty()) {
            dto.setTglPenjualan(convertStringToDate(items[3]));
        }
        if (items.length > 4 && !items[4].trim().isEmpty()) {
            dto.setTglPembelian(convertStringToDate(items[4]));
        }
        if (items.length > 5 && !items[5].trim().isEmpty()) {
            dto.setNilaiPenjualan(normalizeDoubleValue(items[5]));
        }
        if (items.length > 6 && !items[6].trim().isEmpty()) {
            dto.setNilaiPenjualan(normalizeDoubleValue(items[6]));
        }
        if (items.length > 7 && !items[7].trim().isEmpty()) {
            dto.setKodeEfekKolateral(items[7]);
        }
        if (items.length > 8 && !items[8].trim().isEmpty()) {
            dto.setJmlJaminan(normalizeDoubleValue(items[8]));
        }
        if (items.length > 9 && !items[9].trim().isEmpty()) {
            dto.setNilaiPasarWajar(normalizeDoubleValue(items[9]));
        }
        if (items.length > 10 && !items[10].trim().isEmpty()) {
            dto.setNilaiRankingLiabilities(normalizeDoubleValue(items[10]));
        }

        this.vd510a.add(dto);
    }

    private void processVd510B(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysVd510(items[0]);
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
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        // set another value

        this.vd510b.add(dto);
    }

    private void processVd510C(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysVd510(items[0]);
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
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        // set another value

        this.vd510c.add(dto);
    }

    private void processVd510D(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysVd510(items[0]);
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
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        // set another value

        this.vd510d.add(dto);
    }

    private void processVd510E(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysVd510(items[0]);
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
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        // set another value

        this.vd510e.add(dto);
    }

    private void processVd510F(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysVd510(items[0]);
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
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        // set another value

        this.vd510f.add(dto);
    }

    private void processVd510G(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysVd510(items[0]);
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
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        // set another value

        this.vd510g.add(dto);
    }

    private void processVd510H(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysVd510(items[0]);
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
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        // set another value

        this.vd510h.add(dto);
    }

    private void processVd510I(String line, Map<String, String> key){
        String[] items = line.split("\\|");

        List<String> allKeys = getAllKeysVd510(items[0]);
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
        dto.setCreatedBy(this.username);
        dto.setKodeAkun(items[0]);

        // set another value

        this.vd510i.add(dto);
    }

    private double normalizeDoubleValue(String value) {
        value = value.replaceAll(",", ".");
        return Double.parseDouble(value);
    }

    private List<String> getAllKeysVd510(String kodeAkun) {
        String[] kodeAkunArr = kodeAkun.split("\\.");
        List<String> keys = new ArrayList<>();

        String baseKey = kodeAkunArr[0] + "." + kodeAkunArr[1];
        for (int i = 2; i < kodeAkunArr.length; i++) {
            baseKey = baseKey + "." + kodeAkunArr[i];
            keys.add(baseKey);
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
}
