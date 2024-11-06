package dam.pmdm.ejercicio4

class Prisma(val ab:Double, val pb:Double, val h:Double):FiguraGeometrica {
    override fun calcularArea(): Double {
        return (2 * ab) + (pb * h)
    }

    override fun calcularVolumen(): Double {
        return ab * h
    }
}