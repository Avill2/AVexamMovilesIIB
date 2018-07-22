package com.example.andreavillacis.av_exammoviles_iib.Detalle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.OrdenCompra
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.OrdenDetalles
import com.example.daro.carritocompras.R
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseOrdenCompra
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_detalle_orden.*


class DetalleOrdenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var oredenes: OrdenCompra? = null


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_orden)

        oredenes = intent.getParcelableExtra("detallesOrden")

        txtShowCedula.text = oredenes?.cedulaComprador.toString()
        txtShowSector.text = oredenes?.sector
        txtShowIdP.text = oredenes?.idProducto.toString()

        btnGuardarDatosOrden.setOnClickListener { v: View? ->
            guardarDatosOrdenDetalles()

        }

    }

    fun guardarDatosOrdenDetalles(){
        val fechaEnvio = txtFechaEnvio.text.toString()
        val costoProducto = txtPrecioProduc.text.toString().toInt()
        val idProducto = txtShowIdP.text.toString().toInt()
        val ordenDetalles = OrdenDetalles(0, fechaEnvio, costoProducto, idProducto)
        DatabaseOrdenCompra.insertarOrdenDetalles(ordenDetalles)
        Alerter.create(this)
                .setTitle("Orden enviada a CLIENTE")
                .setText("La solicitud fue enviada exitosamente")
                .enableSwipeToDismiss()
                .show()
    }
}
