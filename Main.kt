import javax.swing.JOptionPane

fun main() {
    while (true) {
        val opcion = JOptionPane.showInputDialog("1. Cifrar\n2. Descifrar\n3. Salir")
        if (opcion == "3") return
        val texto = JOptionPane.showInputDialog("Escribe el mensaje:")
        val rieles = JOptionPane.showInputDialog("Número de rieles:").toInt()

        var abajo = true
        var fila = 0
        val rail = Array(rieles) { StringBuilder() }

        for (c in texto) {
            rail[fila].append(c)
            fila += if (abajo) 1 else -1
            if (fila == 0 || fila == rieles - 1) abajo = !abajo
        }

        val resultado = if (opcion == "1") rail.joinToString("") else {
            val posiciones = MutableList(texto.length) { 0 }
            fila = 0
            abajo = true
            for (i in texto.indices) {
                posiciones[i] = fila
                fila += if (abajo) 1 else -1
                if (fila == 0 || fila == rieles - 1) abajo = !abajo
            }
            val orden = IntArray(texto.length)
            var index = 0
            for (i in 0 until rieles) {
                for (j in texto.indices) {
                    if (posiciones[j] == i) orden[j] = index++
                }
            }
            val descifrado = CharArray(texto.length)
            for (i in texto.indices) descifrado[orden[i]] = texto[i]
            String(descifrado)
        }

        JOptionPane.showMessageDialog(null, "Resultado: $resultado")
    }
}
