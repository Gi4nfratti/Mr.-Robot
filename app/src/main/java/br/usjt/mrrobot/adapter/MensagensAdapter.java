package br.usjt.mrrobot.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.usjt.mrrobot.R;
import br.usjt.mrrobot.model.Mensagem;

public class MensagensAdapter extends RecyclerView.Adapter<MensagensAdapter.MyViewHolder> {
    private List<Mensagem> mensagens;
    private Context context;
    private MensagensAdapter adapter;

    public MensagensAdapter(List<Mensagem> lista, Context c) {
        this.mensagens = lista;
        this.context = c;
    }

    @NonNull
    @Override
    public MyViewHolder

    onCreateViewHolder(@NonNull ViewGroup viewGroup,
                       int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View raiz = inflater.inflate(
                R.layout.list_item, viewGroup,
                false);
        return new MyViewHolder(raiz);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        adapter = new MensagensAdapter(mensagens, context);
        Mensagem item = mensagens.get(position);
            myViewHolder.chatNome.setText(item.username);
        if(item.username.equals("Mr. Robot")){
            myViewHolder.cardViewChat.setBackgroundColor(Color.rgb(70, 130, 180));
        }else{
            myViewHolder.cardViewChat.setBackgroundColor(Color.rgb(10,0,153));;
        }
        myViewHolder.chatTextMensagem.setText(item.mensagem);
    }

    @Override
    public int getItemCount() {
        return mensagens.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private CardView cardViewChat;
        private TextView chatNome;
        private TextView chatTextMensagem;
        private RecyclerView recycler;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewChat = itemView.findViewById(R.id.chatCardView);
            recycler = itemView.findViewById(R.id.recyclerMensagens);
            chatNome = itemView.findViewById(R.id.chatNome);
            chatTextMensagem = itemView.findViewById(R.id.chatTextMessage);
        }
    }

}
