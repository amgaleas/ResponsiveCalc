package com.example.practica3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    var input:String = ""
    var query:String = ""
    var place:Int = 0
    var aux:Int = 0
    var count:Int = 0
    val operadores = arrayListOf<Char> ('+', '-', '×', '÷', '^')

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txtInput:TextView = findViewById(R.id.txtInput)
        var txtOutput:TextView = findViewById(R.id.txtOutput)

        findViewById<Button>(R.id.btn0).setOnClickListener {
            when {
                input.isEmpty() || !(input.length == 1 && input.contains('0'))-> {
                    input += "0"
                }
                input.contains('.') -> {
                    input += "0"
                }
            }
            txtInput.setText(query + input)
            realTime(txtOutput)
        }
        findViewById<Button>(R.id.btn1).setOnClickListener {
            numero('1')
            txtInput.setText(query + input)
            realTime(txtOutput)
        }
        findViewById<Button>(R.id.btn2).setOnClickListener {
            numero('2')
            txtInput.setText(query + input)
            realTime(txtOutput)
        }
        findViewById<Button>(R.id.btn3).setOnClickListener {
            numero('3')
            txtInput.setText(query + input)
            realTime(txtOutput)
        }
        findViewById<Button>(R.id.btn4).setOnClickListener {
            numero('4')
            txtInput.setText(query + input)
            realTime(txtOutput)
        }
        findViewById<Button>(R.id.btn5).setOnClickListener {
            numero('5')
            txtInput.setText(query + input)
            realTime(txtOutput)
        }
        findViewById<Button>(R.id.btn6).setOnClickListener {
            numero('6')
            txtInput.setText(query + input)
            realTime(txtOutput)
        }
        findViewById<Button>(R.id.btn7).setOnClickListener {
            numero('7')
            txtInput.setText(query + input)
            realTime(txtOutput)
        }
        findViewById<Button>(R.id.btn8).setOnClickListener {
            numero('8')
            txtInput.setText(query + input)
            realTime(txtOutput)
        }
        findViewById<Button>(R.id.btn9).setOnClickListener {
            numero('9')
            txtInput.setText(query + input)
            realTime(txtOutput)
        }

        findViewById<Button>(R.id.btnPunto).setOnClickListener {
            if (input.isEmpty()) {
                input += "0."
            } else if (!input.contains('.')) {
                input += "."
            }
            txtInput.setText(query + input)
            realTime(txtOutput)
        }
        findViewById<Button>(R.id.btnSum).setOnClickListener {
            operador('+')
            txtInput.setText(query + input)
            txtOutput.setText("")
        }
        findViewById<Button>(R.id.btnRes).setOnClickListener {
            operador('-')
            txtInput.setText(query + input)
            txtOutput.setText("")
        }
        findViewById<Button>(R.id.btnMul).setOnClickListener {
            operador('×')
            txtInput.setText(query + input)
            txtOutput.setText("")
        }
        findViewById<Button>(R.id.btnDiv).setOnClickListener {
            operador('÷')
            txtInput.setText(query + input)
            txtOutput.setText("")
        }
        findViewById<Button>(R.id.btnExp).setOnClickListener {
            operador('^')
            txtInput.setText(query + input)
            txtOutput.setText("")
        }
        // HACER UNA FUNCIÓN PARA ACORTAR CÓDIGO CON PARÁMETROS POR REFERENCIA

        findViewById<Button>(R.id.btnAC).setOnClickListener {
            input = ""
            query = ""
            txtInput.setText("")
            txtOutput.setText("")
        }
        findViewById<Button>(R.id.btnBorrar).setOnClickListener {
            if (!input.isEmpty()) {
                input = input.dropLast(1)
            } else if (!query.isEmpty()) {
                place = 0
                aux = 0
                count = 0
                query = query.dropLast(1)

                for (caracter in operadores) {
                    place = query.lastIndexOf(caracter)
                    if (place > aux) {
                        aux = place
                    } else {
                        count++
                    }
                }

                if (count != 5) {
                    aux++
                }
                input = query.substring(aux)
                query = query.dropLast(input.length)
            }

            txtInput.setText(query + input)
            realTime(txtOutput)
        }

        findViewById<Button>(R.id.btnIgual).setOnClickListener {
            if (input.isEmpty()) {
                Toast.makeText(this@MainActivity, "No se ingresó el último número", Toast.LENGTH_SHORT).show()
            } else {
                input = suma(query + input)
                query = ""
                txtInput.setText(input)
                txtOutput.setText("")
            }
        }
    }

    private fun numero(caracter:Char) {
        if (input.length == 1 && input.contains('0')){
            input = caracter.toString()
        } else {
            input += caracter
        }
    }
    private fun operador(caracter:Char) {
        when {
            !query.isEmpty() && input.isEmpty() -> {
                query = query.dropLast(1) + caracter
            }
            !input.isEmpty() -> {
                query += input + caracter
                input = ""
            }
            /*
            // No hay problema con que quede "#." porque el .toDouble() lo convierte en "#.0"
            !input.isEmpty() && !input.last().equals('.') -> {
                query += input + caracter
                input = ""
            }
            !input.isEmpty() && input.last().equals('.') -> {
                query += input + "0" + caracter
                input = ""
            }
            */
        }
    }
    private fun realTime(txtOutput:TextView) {
        if (!input.isEmpty() && input.toDoubleOrNull() != null) {
            txtOutput.setText(suma(query + input))
        } else {
            txtOutput.setText("")
        }
    }

    private fun suma(operacion:String):String {
        var operandos:Array<String> = operacion.split('+').toTypedArray()

        for (place in operandos.indices) {
            if (operandos[place].toDoubleOrNull() == null) {
                operandos[place] = resta(operandos[place])
            }
        }

        var resultado = operandos[0].toDouble()
        if (operandos.size > 1) {
            for (place in 1 until operandos.size) {
                resultado += operandos[place].toDouble()
            }
        }

        return resultado.toString()
    }
    private fun resta(operacion:String):String {
        var operandos:Array<String> = operacion.split('-').toTypedArray()

        for (place in operandos.indices) {
            if (operandos[place].toDoubleOrNull() == null) {
                operandos[place] = multiplicacion(operandos[place])
            }
        }

        var resultado = operandos[0].toDouble()
        if (operandos.size > 1) {
            for (place in 1 until operandos.size) {
                resultado -= operandos[place].toDouble()
            }
        }

        return resultado.toString()
    }
    private fun multiplicacion(operacion:String):String {
        var operandos:Array<String> = operacion.split('×').toTypedArray()

        for (place in operandos.indices) {
            if (operandos[place].toDoubleOrNull() == null) {
                operandos[place] = division(operandos[place])
            }
        }

        var resultado = operandos[0].toDouble()
        if (operandos.size > 1) {
            for (place in 1 until operandos.size) {
                resultado *= operandos[place].toDouble()
            }
        }

        return resultado.toString()
    }
    private fun division(operacion:String):String {
        var operandos:Array<String> = operacion.split('÷').toTypedArray()

        for (place in operandos.indices) {
            if (operandos[place].toDoubleOrNull() == null) {
                operandos[place] = potencia(operandos[place])
            }
        }

        var resultado = operandos[0].toDouble()
        if (operandos.size > 1) {
            for (place in 1 until operandos.size) {
                resultado /= operandos[place].toDouble()
            }
        }

        return resultado.toString()
    }
    private fun potencia(operacion:String):String {
        var operandos:List<String> = operacion.split('^').toList()
        var resultado = 1.0

        for (place in operandos.indices.reversed()) {
            resultado = Math.pow(operandos[place].toDouble(), resultado)
        }

        return resultado.toString()
    }
}