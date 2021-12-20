package usa.retos.mireto.modelos;

public class Sucursal {
    private int id;
    private String name;
    private String localization;
    private byte[] image;

    public Sucursal(int id, String name, String localization, byte[] image) {
        this.id = id;
        this.name = name;
        this.localization = localization;
        this.image = image;
    }

    public Sucursal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
