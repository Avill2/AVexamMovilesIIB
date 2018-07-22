package com.example.daro.carritocompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_datos_comprador.*
import android.content.Intent
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.OrdenCompra
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseOrdenCompra


class DatosCompradorActivity : AppCompatActivity() {

    lateinit var idProducto:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_comprador)

       idProducto = intent.getStringExtra("idProducto")

        //Toast.makeText(this,idProducto,Toast.LENGTH_SHORT).show()

        btnEnviarDatosComprador.setOnClickListener { v: View? ->
            crearOreden()
        }
    }

    fun crearOreden(){
        var cedula = txtCedulaComprador.text.toString().toInt()
        var sector = txtSector.text.toString()
        var idProducto = idProducto.toString().toInt()

        var oredenCompra = OrdenCompra(0, cedula, sector, idProducto)
        DatabaseOrdenCompra.insertarOrden(oredenCompra)

        Alerter.create(this)
                .setTitle("Datos Enviados a DELIVERY")
                .setText("Su orden ha sido enviada satisfactoriamente")
                .setDuration(10000)
                .enableSwipeToDismiss()
                .setOnClickListener(View.OnClickListener {
                    irAbuscarTienda()
                }).show()


    }

    fun irAbuscarTienda(){
        txtCedulaComprador.setText("")
        txtSector.setText("")
        val intent = Intent(this,BuscarConductorActivity::class.java)
        startActivity(intent)
    }
}
