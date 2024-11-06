package dam.pmdm.ejercicio4

class Piramide(val ab:Double, val pb:Double, val ap:Double, val h:Double):FiguraGeometrica {
    override fun calcularArea(): Double {
        return ab + ((pb*ap)/2)
    }

    override fun calcularVolumen(): Double {
        return (ab * h)/3
    }
}