class Shape {
    public double area() {
        return 0;
    }
}

class Triangle extends Shape {
    double height;
    double base;

    // override the method here

    @Override
    public double area() {
        return base*height / 2f;
    }
}

class Circle extends Shape {
    double radius;

    @Override
    public double area() {
        return Math.PI * Math.pow(radius,2);
    }
// override the method here
}

class Square extends Shape {
    double side;

    @Override
    public double area() {
        return Math.pow(side, 2);
    }
// override the method here
}

class Rectangle extends Shape {
    double width;
    double height;

    @Override
    public double area() {
        return width * height;
    }
// override the method here
}