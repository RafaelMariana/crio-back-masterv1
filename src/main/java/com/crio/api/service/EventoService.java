package com.crio.api.service;

import com.crio.api.domain.evento.Evento;
import com.crio.api.domain.evento.EventoRequestDTO;
import com.crio.api.domain.evento.IntervaloDataDTO;
import com.crio.api.repositorie.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;


    public Evento createEvento(EventoRequestDTO data) {
        //cria um evento vazio
        Evento newEvento = new Evento();
        //preenche os dados do evento
        newEvento.setTitulo(data.titulo());
        newEvento.setDescricao(data.descricao());
        newEvento.setInicio(data.inicio());
        newEvento.setFim(data.fim());
        newEvento.setLocal(data.local());
        newEvento.setPrivado(data.privado());
        newEvento.setLinkEvento(data.linkEvento());
        newEvento.setComoChegar(data.comoChegar());
        newEvento.setLinkForms(data.linkForms());
        newEvento.setUsuario(data.usuario());
        newEvento.setEndereco(data.endereco());
        eventoRepository.save(newEvento);
        return newEvento;
    }


    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    public Evento getEventoById(UUID id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado."));
    }

    public Evento updateEvento(UUID id, EventoRequestDTO eventoRequestDTO) {
        Evento updateEvento = getEventoById(id);
        updateEvento.setTitulo(eventoRequestDTO.titulo());
        updateEvento.setDescricao(eventoRequestDTO.descricao());
        updateEvento.setInicio(eventoRequestDTO.inicio());
        updateEvento.setFim(eventoRequestDTO.fim());
        updateEvento.setLocal(eventoRequestDTO.local());
        updateEvento.setPrivado(eventoRequestDTO.privado());
        updateEvento.setLinkEvento(eventoRequestDTO.linkEvento());
        updateEvento.setComoChegar(eventoRequestDTO.comoChegar());
        updateEvento.setLinkForms(eventoRequestDTO.linkForms());
        updateEvento.setUsuario(eventoRequestDTO.usuario());
        updateEvento.setEndereco(eventoRequestDTO.endereco());
        return eventoRepository.save(updateEvento);
    }

    public void deleteEvento(UUID id) {
        Evento evento = getEventoById(id);
        eventoRepository.delete(evento);
    }

    public List<Evento> findByUsuarioId(UUID usuarioId) {
        return eventoRepository.findByUsuarioId(usuarioId);

    }

    public List<Evento> findByIntervaloData(IntervaloDataDTO intervaloDataDTO) {
        LocalDateTime inicio = intervaloDataDTO.inicio();
        LocalDateTime fim = intervaloDataDTO.fim();
        return eventoRepository. findByIntervaloData(inicio, fim);


    }

    public List<Evento> findByLocal(String local) {
        return eventoRepository.findByLocal(local);

    }
    public List<Evento>findByLocalAndIntervaloData(String local, LocalDateTime inicio,LocalDateTime fim){
        return  eventoRepository.findByLocalAndIntervaloData(local,inicio,fim);
    }


}
