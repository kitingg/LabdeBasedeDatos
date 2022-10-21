package com.example.labdebasededatos;

import androidx.appcompat.app.AppCompatActivity; import android.app.AlertDialog;
import android.content.ContentValues; import android.content.Context;
import android.content.DialogInterface; import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase; import android.os.Bundle;
import android.view.View; import android.widget.Button; import android.widget.EditText; import android.widget.Toast;

public class MainActivity extends AppCompatActivity { EditText et_nombre;
    EditText et_correo; Button guardar; Button borrar; Button actualizar; Button adelante; Button atras; Cursor c;
    AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);

//definir los controles que estan en el main.xml

        et_nombre = (EditText) findViewById(R.id.et1); et_correo = (EditText) findViewById(R.id.et2);

        guardar = (Button) findViewById(R.id.bt_insert); borrar = (Button) findViewById(R.id.bt_delete); actualizar = (Button) findViewById(R.id.bt_update);

        adelante = (Button) findViewById(R.id.bt_adelante); atras = (Button) findViewById(R.id.bt_atras);

//Abrir la bd en modo escritura
        ClaseSQLiteBD1 csql = new ClaseSQLiteBD1(this, "Estudiantes.db", null, 1); final SQLiteDatabase db = csql.getWritableDatabase();

//creando un cursor para obtener los datos de la BD
c = db.rawQuery("select * from contacto", null);

//declaracion del proveedor de contenidos
        final ContentValues nuevoRegistro = new ContentValues(); alertDialogBuilder = new AlertDialog.Builder(this);

//boton guardar
        guardar.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
//seteamos los registros al contenedor
nuevoRegistro.put("NOMBRE", et_nombre.getText().toString()); nuevoRegistro.put("CORREO", et_correo.getText().toString());
//insertamos el nuevo registro a la BD usando insert
db.insert("contacto", null, nuevoRegistro);
            Toast.makeText(getApplicationContext(), "Contacto añadido correctamente", Toast.LENGTH_LONG).show(); et_nombre.setText("");
            et_correo.setText("");
            c = db.rawQuery("select * from contacto", null);
        }
        });

//boton borrar
        borrar.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {


// Configura el titulo.
alertDialogBuilder.setTitle("Confirmacion");
// Configura el mensaje.
alertDialogBuilder
.setMessage(" ¿Está seguro de eliminar el registro?")
                    .setCancelable(false)
                    .setPositiveButton("Si",new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog,int id) { String ID = String.valueOf(c.getInt(0)); db.delete("contacto", "_id=?", new String[]{ID}); et_nombre.setText("");
                            et_correo.setText("");
                            Context context = getApplicationContext(); CharSequence text = "REGISTRO ELIMINADO";

                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(context, text, duration); toast.show();
                            c = db.rawQuery("select * from contacto", null);
                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() { public void onClick(DialogInterface dialog,int id) { dialog.cancel();
                    }
                    }).create().show();
        }
        });

//actualizar
        actualizar.setOnClickListener(new View.OnClickListener() { public void onClick(View arg0) {



// Configura el titulo.
alertDialogBuilder.setTitle("Confirmacion");
// Configura el mensaje.
alertDialogBuilder
.setMessage(" ¿Está seguro de Actualizar el registro?")
                    .setCancelable(false)
                    .setPositiveButton("Si",new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog,int id) {

                            ContentValues nuevoRegistro = new ContentValues(); nuevoRegistro.put("NOMBRE", et_nombre.getText().toString()); nuevoRegistro.put("CORREO", et_correo.getText().toString()); String ID = String.valueOf(c.getInt(0));
                            int cant = db.update("contacto", nuevoRegistro, "_id = ?", new String[]{ID}); if (cant == 1){
                                Toast.makeText(getApplicationContext(), "se modificaron los datos",Toast.LENGTH_SHORT).show(); c = db.rawQuery("select * from contacto", null);
                                et_nombre.setText(""); et_correo.setText("");
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "no existe un contacto con dicho documento",Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() { public void onClick(DialogInterface dialog,int id) {

                        dialog.cancel();
                    }
                    }).create().show();
        }
        });

//boton atras
        atras.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
// TODO Auto-generated method stub
try {
//mueve el cursor una posicion atras
c.moveToPrevious(); et_nombre.setText(c.getString(1)); et_correo.setText(c.getString(2));
        } catch (Exception e) {

            c.moveToLast(); et_nombre.setText(c.getString(1)); et_correo.setText(c.getString(2));
        }
        }

    });

//boton adelante
adelante.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
// TODO Auto-generated method stub
try {
//mueve el cursor una posicion adelante
c.moveToNext(); et_nombre.setText(c.getString(1)); et_correo.setText(c.getString(2));
    } catch (Exception e) { c.moveToFirst(); et_nombre.setText(c.getString(1)); et_correo.setText(c.getString(2));
    }
    }

});
        }
        }

