package assignment.canvas.commands;

import static assignment.CanvasConstants.INCORRECT_DIMENSIONS;

import java.util.stream.IntStream;

import assignment.Point;
import assignment.canvas.Canvas;
import assignment.validation.CanvasValidation;

public class DrawLineCommand implements DrawCommand {

	Character toDraw;
	private Point startPoint;
	private Point endPoint;

	public DrawLineCommand(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.toDraw = 'X';
	}

	@Override
	public Canvas execute(Canvas canvas) {
		canvas.setCanvasValid(true);
		CanvasValidation.validateCanvas(canvas);
		CanvasValidation.validatePoints(startPoint, endPoint, canvas);

		if (!canvas.isCanvasValid()) {
			System.out.println(INCORRECT_DIMENSIONS);
			return canvas;
		}

		char canvasArray[][] = canvas.getCanvasArea();

//        for(int i=startPoint.getY(); i<=endPoint.getY(); i++) {
//            for(int j=startPoint.getX(); j<=endPoint.getX(); j++) {
//                canvasArray[i][j] = toDraw;
//            }
//        }

		int xdiff = endPoint.getX() - startPoint.getX();
		int ydiff = endPoint.getY() - startPoint.getY();
		
		int numPoints = (Math.abs(xdiff) > Math.abs(ydiff)) ? xdiff : ydiff;
		for (int i = 0; i <= numPoints; i++) {
			int x = 0;
			int y = 0;
			if (i == 0) {
				x = startPoint.getX();
				y = startPoint.getY();
			} else {
				x = startPoint.getX() + (int) Math.round((double) xdiff * i / Math.abs(numPoints));
				y = startPoint.getY() + (int) Math.round((double) ydiff * i / Math.abs(numPoints));
			}

			canvasArray[x][y] = toDraw;
		}
		return canvas;
	}

}
