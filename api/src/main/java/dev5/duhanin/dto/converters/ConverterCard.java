package dev5.duhanin.dto.converters;

import dev5.duhanin.dto.CardDTO;
import dev5.duhanin.entity.Card;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterCard {
    @Autowired
    private ModelMapper modelMapper;

    public CardDTO cardToDTO(Card card) {
        CardDTO cardDTO = modelMapper.map(card, CardDTO.class);
        return cardDTO;
    }

    public Card cardToEntity(CardDTO cardDTO) {
        Card card = modelMapper.map(cardDTO, Card.class);
        return card;
    }
}
