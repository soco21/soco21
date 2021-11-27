package ch.uzh.group8.checkersv3.dom.board;

import static ch.uzh.group8.checkersv3.dom.BoardCoordinates.Column.*;
import static ch.uzh.group8.checkersv3.dom.BoardCoordinates.Row.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import ch.uzh.group8.checkersv3.dom.BoardCoordinates;
import ch.uzh.group8.checkersv3.dom.Piece;
import ch.uzh.group8.checkersv3.dom.Player;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StoreTest {
  private static final Piece WHITE_PIECE = new Piece(Player.PLAYER_WHITE, false);
  private static final BoardCoordinates EMPTY_COORDINATES_AT_START = new BoardCoordinates(ROW_4, A);
  private static final BoardCoordinates OCCUPIED_COORDINATES_AT_START =
      new BoardCoordinates(ROW_6, B);
  private static final Piece RED_PIECE = new Piece(Player.PLAYER_RED, false);
  private Store store;

  @BeforeEach
  public void setup() {
    store = new Store();
  }

  @Test
  public void add_piece_on_empty_place() {
    assertThat(store.getPieceAt(EMPTY_COORDINATES_AT_START), is(Optional.empty()));
    store.addPiece(EMPTY_COORDINATES_AT_START, WHITE_PIECE);
    assertThat(store.getPieceAt(EMPTY_COORDINATES_AT_START), is(Optional.of(WHITE_PIECE)));
  }

  @Test
  public void add_piece_on_occupied_place() {
    assertThat(store.getPieceAt(OCCUPIED_COORDINATES_AT_START), is(Optional.of(RED_PIECE)));
    store.addPiece(OCCUPIED_COORDINATES_AT_START, WHITE_PIECE);
    assertThat(store.getPieceAt(OCCUPIED_COORDINATES_AT_START), is(Optional.of(WHITE_PIECE)));
  }

  @Test
  public void remove_piece_at_empty_place() {
    store.removePiece(EMPTY_COORDINATES_AT_START);
    assertThat(store.getPieceAt(EMPTY_COORDINATES_AT_START), is(Optional.empty()));
  }

  @Test
  public void remove_piece_at_occupied_place() {
    store.removePiece(OCCUPIED_COORDINATES_AT_START);
    assertThat(store.getPieceAt(OCCUPIED_COORDINATES_AT_START), is(Optional.empty()));
  }

  @Test
  public void get_piece_of_empty_place() {
    assertThat(store.getPieceAt(EMPTY_COORDINATES_AT_START), is(Optional.empty()));
  }

  @Test
  public void get_piece_at_occupied_place() {
    assertThat(store.getPieceAt(OCCUPIED_COORDINATES_AT_START), is(Optional.of(RED_PIECE)));
  }
}
