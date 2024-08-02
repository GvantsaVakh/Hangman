
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;
import java.awt.Font;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		addLines();
	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		addFirstLabel(word);
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user.
	 * Calling this method causes the next body part to appear on the scaffold
	 * and adds the letter to the list of incorrect guesses that appears at the
	 * bottom of the window.
	 */
	public void noteIncorrectGuess(int mistake, String characters) {
		//label2 marks the mistakes
		if (label2 != null) {
			remove(label2);
		}
		label2 = new GLabel(characters);
		Font secondFont = new Font("SansSerif", Font.PLAIN, 16);
		label2.setFont(secondFont);
		label2.setColor(Color.BLACK);
		add(label2, getWidth() / 2 - BEAM_LENGTH, getHeight() - 15);

		if (mistake == 1) {
			addHead();
		}
		if (mistake == 2) {
			addBody();
		}
		if (mistake == 3) {
			addLeftHand();
		}
		if (mistake == 4) {
			addRightHand();
		}
		if (mistake == 5) {
			addLeftLeg();
		}
		if (mistake == 6) {
			addRightLeg();
		}
		if (mistake == 7) {
			addLeftFoot();
		}
		if (mistake == 8) {
			addRightFoot();
		}
	}

	private void addHead() {
		GOval head = new GOval(getWidth() / 2 - HEAD_RADIUS, TOP_OFFSET + ROPE_LENGTH, 2 * HEAD_RADIUS,
				2 * HEAD_RADIUS);
		add(head);

	}

	private void addBody() {
		GLine body = new GLine(getWidth() / 2, TOP_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS, getWidth() / 2,
				+TOP_OFFSET + ROPE_LENGTH + BODY_LENGTH + 2 * HEAD_RADIUS);
		add(body);
	}

	private void addLeftHand() {
		GLine leftUpperArm = new GLine(getWidth() / 2, TOP_OFFSET + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
				getWidth() / 2 - UPPER_ARM_LENGTH, TOP_OFFSET + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		add(leftUpperArm);
		GLine leftLowerArm = new GLine(getWidth() / 2 - UPPER_ARM_LENGTH,
				TOP_OFFSET + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, getWidth() / 2 - UPPER_ARM_LENGTH,
				TOP_OFFSET + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(leftLowerArm);
	}

	private void addRightHand() {
		GLine rightUpperArm = new GLine(getWidth() / 2, TOP_OFFSET + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
				getWidth() / 2 + UPPER_ARM_LENGTH, TOP_OFFSET + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		add(rightUpperArm);
		GLine rightLowerArm = new GLine(getWidth() / 2 + UPPER_ARM_LENGTH,
				TOP_OFFSET + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, getWidth() / 2 + UPPER_ARM_LENGTH,
				TOP_OFFSET + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(rightLowerArm);
	}

	private void addLeftLeg() {
		GLine leftHip = new GLine(getWidth() / 2, TOP_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
				getWidth() / 2 - HIP_WIDTH, TOP_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		add(leftHip);
		GLine leftLeg = new GLine(getWidth() / 2 - HIP_WIDTH, TOP_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
				getWidth() / 2 - HIP_WIDTH, TOP_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		add(leftLeg);
	}

	private void addRightLeg() {
		GLine leftHip = new GLine(getWidth() / 2, TOP_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
				getWidth() / 2 + HIP_WIDTH, TOP_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		add(leftHip);
		GLine leftLeg = new GLine(getWidth() / 2 + HIP_WIDTH, TOP_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
				getWidth() / 2 + HIP_WIDTH, TOP_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		add(leftLeg);
	}

	private void addLeftFoot() {
		GLine leftFoot = new GLine(getWidth() / 2 - HIP_WIDTH,
				TOP_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
				getWidth() / 2 - HIP_WIDTH - FOOT_LENGTH,
				TOP_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		add(leftFoot);
	}

	private void addRightFoot() {
		GLine leftFoot = new GLine(getWidth() / 2 + HIP_WIDTH,
				TOP_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
				getWidth() / 2 + HIP_WIDTH + FOOT_LENGTH,
				TOP_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		add(leftFoot);
		iqsebi(getWidth() / 2 - HEAD_RADIUS + 15);
		iqsebi(getWidth() / 2 - HEAD_RADIUS + 44);
	}

	//label that shows the current word with guessed characters
	private void addFirstLabel(String word) {
		GLabel label1 = new GLabel(word);
		Font firstFont = new Font("SansSerif", Font.PLAIN, 26);
		label1.setFont(firstFont);
		label1.setColor(Color.BLACK);
		add(label1, getWidth() / 2 - BEAM_LENGTH, getHeight() - 40);
	}

	
	//lines for scaffold, beam and rope
	private void addLines() {
		GLine scaffold = new GLine(getWidth() / 2 - BEAM_LENGTH, TOP_OFFSET + SCAFFOLD_HEIGHT,
				getWidth() / 2 - BEAM_LENGTH, TOP_OFFSET);
		add(scaffold);
		GLine beam = new GLine(getWidth() / 2 - BEAM_LENGTH, TOP_OFFSET, getWidth() / 2, TOP_OFFSET);
		add(beam);
		GLine rope = new GLine(getWidth() / 2, TOP_OFFSET, getWidth() / 2, TOP_OFFSET + ROPE_LENGTH);
		add(rope);
	}

	//appears when the user loses
	private void iqsebi(int x) {
		GLine line1 = new GLine(x, TOP_OFFSET + ROPE_LENGTH + 20, x + TVALI_L, TOP_OFFSET + ROPE_LENGTH + 20 + TVALI_L);
		add(line1);
		GLine line2 = new GLine(x, TOP_OFFSET + ROPE_LENGTH + 20 + TVALI_L, x + TVALI_L, TOP_OFFSET + ROPE_LENGTH + 20);
		add(line2);

	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int TOP_OFFSET = 30;
	private static final int TVALI_L = 14;
	private GLabel label2;

}
