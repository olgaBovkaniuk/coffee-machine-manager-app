package by.pvt.service;

public enum ImageSize {
    SMALL(100, 100),
    LARGE(500, 500);

    public final int width;
    public final int height;

    ImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
