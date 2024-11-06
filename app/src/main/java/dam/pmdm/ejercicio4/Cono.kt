package dam.pmdm.ejercicio4

class Cono(val r:Double, val g:Double, val h:Double):FiguraGeometrica {
    override fun calcularArea(): Double {
        return Math.PI * r * (r + g)
    }

    override fun calcularVolumen(): Double {
        return (Math.PI * Math.pow(r, 2.2) * h)/3
    }
}