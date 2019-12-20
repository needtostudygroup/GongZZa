package com.dongkyoo.gongzza.customViews.timetableView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.dtos.CourseDto;
import com.dongkyoo.gongzza.vos.Course;
import com.dongkyoo.gongzza.vos.CourseInfo;
import com.dongkyoo.gongzza.vos.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimetableView extends View {

    private List<UserTimetable> userTimetableList;

    private int tableWidth;
    private int tableHeight;
    private Context context;

    private String[] weekDays;
    private int height;
    private int cellWidth;
    private int cellHeight;
    private int mostLeftColumnWidth;
    private int topRowHeight;
    private int userListLayoutHeight;
    private String[] colorList;
    private Rect checkboxRect;
    private Rect uncheckboxRect;

    private static final int MIN_HOUR = 9;
    private static final int MAX_HOUR = 21;

    private static final int BIG_TEXT_SIZE = 50;
    private static final int SMALL_TEXT_SIZE = 40;

    private static final int MIN_CELL_HEIGHT = 150;
    private static final int CELL_MARGIN = 5;

    private List<RectF> courseInfoRectList;
    private List<CourseData> courseList;
    private List<OnClickCourseInfoListener> clickCourseInfoListenerList;

    private Bitmap checkedCheckbox;
    private Bitmap uncheckedCheckbox;

    private int userNameListRectTop;
    private int userNameListRectLeft;

    public TimetableView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TimetableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public TimetableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public interface OnClickCourseInfoListener {
        void onClick(Course course, CourseInfo courseInfo);
    }

    private void init() {
        checkedCheckbox = BitmapFactory.decodeResource(context.getResources(), R.drawable.round_check_box_black_24);
        uncheckedCheckbox = BitmapFactory.decodeResource(context.getResources(), R.drawable.round_check_box_outline_blank_black_24);

        checkboxRect = new Rect(0, 0, checkedCheckbox.getWidth(), checkedCheckbox.getHeight());
        uncheckboxRect = new Rect(0, 0, uncheckedCheckbox.getWidth(), uncheckedCheckbox.getHeight());

        courseInfoRectList = new ArrayList<>();
        courseList = new ArrayList<>();

        clickCourseInfoListenerList = new ArrayList<>();
        userTimetableList = new ArrayList<>();
        colorList = context.getResources().getStringArray(R.array.color_list);
    }

    public void setOnClickCourseInfoListener(OnClickCourseInfoListener listener) {
        clickCourseInfoListenerList.add(listener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int colorIndex = 0;
        courseInfoRectList = new ArrayList<>();
        courseList = new ArrayList<>();

        Paint p = new Paint();

        // 월화수목금
        p.setTextSize(BIG_TEXT_SIZE);
        for (int i = 0; i < weekDays.length; i++) {
            p.setColor(Color.BLACK);
            canvas.drawText(weekDays[i], (float) i * cellWidth + cellWidth / 2 - BIG_TEXT_SIZE / 2 + mostLeftColumnWidth, BIG_TEXT_SIZE, p);

            p.setColor(Color.GRAY);
            int x = (i + 1) * cellWidth + mostLeftColumnWidth;
            canvas.drawLine(x , 0, x, tableHeight, p);
        }

        // 시간
        p.setTextSize(SMALL_TEXT_SIZE);
        for (int i = MIN_HOUR; i <= MAX_HOUR; i++) {
            p.setColor(Color.BLACK);
            canvas.drawText(String.valueOf(i), 0, cellHeight * (i - MIN_HOUR) + SMALL_TEXT_SIZE / 2 + topRowHeight, p);

            p.setColor(Color.GRAY);
            int y = cellHeight * (i - MIN_HOUR + 1) + topRowHeight;
            canvas.drawLine(mostLeftColumnWidth, y, tableWidth, y, p);
        }

        p.setColor(Color.GRAY);
        canvas.drawLine(mostLeftColumnWidth, topRowHeight, tableWidth, topRowHeight, p);
        canvas.drawLine(mostLeftColumnWidth, 0, mostLeftColumnWidth, tableHeight, p);

        p.setColor(Color.WHITE);
        for (UserTimetable timetable: userTimetableList) {
            if (timetable.isChecked) {
                p.setColor(Color.WHITE);
                for (CourseDto courseDto : timetable.courseDtoList) {
                    for (CourseInfo info : courseDto.getCourseInfoList()) {
                        int day = info.getDay();
                        Paint cellPaint = new Paint();
                        if (timetable.isRandomColorMode) {
                            cellPaint.setColor(Color.parseColor(colorList[colorIndex++]));
                        } else {
                            cellPaint.setColor(Color.parseColor(timetable.color));
                        }
                        if (day >= Calendar.MONDAY && day <= Calendar.FRIDAY) {
                            Calendar c = Calendar.getInstance();

                            c.setTime(info.getStartTime());
                            int startHour = c.get(Calendar.HOUR_OF_DAY);
                            int startMin = c.get(Calendar.MINUTE);

                            c.setTime(info.getEndTime());
                            int endHour = c.get(Calendar.HOUR_OF_DAY);
                            int endMin = c.get(Calendar.MINUTE);

                            float startX = mostLeftColumnWidth + cellWidth * (day - Calendar.MONDAY);
                            float startY = topRowHeight + cellHeight * (startHour - MIN_HOUR) + cellHeight * (startMin / 60f);

                            float endX = startX + cellWidth;
                            float endY = topRowHeight + cellHeight * (endHour - MIN_HOUR) + cellHeight * (endMin / 60f);

                            RectF rectF = new RectF(startX, startY, endX, endY);
                            courseInfoRectList.add(rectF);
                            courseList.add(new CourseData(courseDto, info));

                            canvas.drawRect(rectF, cellPaint);

                            p.setFakeBoldText(true);
                            float nextY = 0;

                            List<String> nameList = split(courseDto.getName(), cellWidth - CELL_MARGIN, p);
                            for (int i = 0; i < nameList.size(); i++) {
                                nextY = startY + p.getTextSize() * (i + 1);

                                canvas.drawText(nameList.get(i), startX + 5, nextY, p);
                            }

                            p.setFakeBoldText(false);
                            nameList = split(courseDto.getProfessor(), cellWidth - CELL_MARGIN, p);
                            nextY += 10;
                            for (int i = 0; i < nameList.size(); i++) {
                                nextY += p.getTextSize();
                                canvas.drawText(nameList.get(i), startX + 5, nextY, p);
                            }
                        }
                    }
                }
            }

            if (timetable.isRandomColorMode) {
                p.setColor(Color.BLACK);
            } else {
                p.setColor(Color.parseColor(timetable.color));
            }

            if (timetable.color != null) {
                p.setColorFilter(new PorterDuffColorFilter(Color.parseColor(timetable.color), PorterDuff.Mode.SRC_IN));
            }
            if (timetable.isChecked) {
                canvas.drawBitmap(checkedCheckbox, checkboxRect, timetable.checkboxRect, p);
            } else {
                canvas.drawBitmap(uncheckedCheckbox, checkboxRect, timetable.checkboxRect, p);
            }
            p.setColorFilter(null);

            p.setColor(Color.BLACK);
            canvas.drawText(timetable.user.getName(), timetable.checkboxRect.right + 30, timetable.checkboxRect.top + p.getTextSize() + 5, p);
        }
    }

    private List<String> split(String str, float width, Paint p) {
        List<String> ret = new ArrayList<>();
        int t = 0;
        while (t < str.length()) {
            String s = null;
            for (int i = t, j = i; j < str.length() && p.measureText(str, i, j + 1) < width; j++, t++) {
                s = str.substring(i, j + 1);
            }
            ret.add(s);
        }

        return ret;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        tableWidth = MeasureSpec.getSize(widthMeasureSpec);

        weekDays = context.getResources().getStringArray(R.array.weekday_list);

        mostLeftColumnWidth = SMALL_TEXT_SIZE * 2 + 15;
        topRowHeight = BIG_TEXT_SIZE + 30;

        cellWidth = (tableWidth - mostLeftColumnWidth) / weekDays.length;
        if (cellHeight < MIN_CELL_HEIGHT) {
            cellHeight = MIN_CELL_HEIGHT;
        }

        tableHeight = cellHeight * (MAX_HOUR - MIN_HOUR + 1) + topRowHeight + 30;
        if (userNameListRectTop == 0) {
            userNameListRectTop = tableHeight + 30;
            userNameListRectLeft = 30;
        }

        userListLayoutHeight = 50;
        height = tableHeight + userListLayoutHeight + 30 + 50/*userNameListHeight*/;
        setMeasuredDimension(tableWidth, height);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP && event.getEventTime() - event.getDownTime() < 300) {
            for (int i = 0; i < courseInfoRectList.size(); i++) {
                if (courseInfoRectList.get(i).contains(event.getX(), event.getY())) {
                    for (OnClickCourseInfoListener listener : clickCourseInfoListenerList) {
                        listener.onClick(courseList.get(i).course, courseList.get(i).courseInfo);
                    }
                    return true;
                }
            }

            for (UserTimetable userTimetable : userTimetableList) {
                if (userTimetable.checkboxRect.contains((int) event.getX(), (int) event.getY())) {
                    userTimetable.isChecked = !userTimetable.isChecked;
                    invalidate();
                    return true;
                }
            }
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            for (int i = 0; i < courseInfoRectList.size(); i++) {
                if (courseInfoRectList.get(i).contains(event.getX(), event.getY())) {
                    return true;
                }
            }

            for (UserTimetable userTimetable : userTimetableList) {
                if (userTimetable.checkboxRect.contains((int) event.getX(), (int) event.getY())) {
                    return true;
                }
            }
        }

        return false;
    }

    public void addCourseDtoList(List<CourseDto> courseDtoList) {
        courseDtoList.addAll(courseDtoList);
        invalidate();
    }

    public List<UserTimetable> getUserTimetableList() {
        return userTimetableList;
    }

    public void setUserTimetableList(List<UserTimetable> userTimetableList) {
        this.userTimetableList = userTimetableList;
        invalidate();
    }

    public void addUserTimetableList(UserTimetable userTimetable) {
        boolean isExist = false;
        for (UserTimetable t : userTimetableList) {
            if (t.user.getId().equals(userTimetable.getUser().getId())) {
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            this.userTimetableList.add(userTimetable);
            Paint p = new Paint();
            p.setTextSize(SMALL_TEXT_SIZE);
            if (userNameListRectLeft + p.measureText(userTimetable.user.getName()) > tableWidth) {
                userNameListRectTop += checkedCheckbox.getHeight() + 15;
                userNameListRectLeft = 30;
            }

            userTimetable.checkboxRect = new Rect(userNameListRectLeft, userNameListRectTop, userNameListRectLeft + checkedCheckbox.getWidth(), userNameListRectTop + checkedCheckbox.getHeight());
            userNameListRectLeft += userTimetable.checkboxRect.width() + p.measureText(userTimetable.user.getName()) + 60;
        }
        invalidate();
    }

    private static final class CourseData {
        Course course;
        CourseInfo courseInfo;

        public CourseData(Course course, CourseInfo courseInfo) {
            this.course = course;
            this.courseInfo = courseInfo;
        }
    }

    public static class UserTimetable {

        private boolean isChecked = true;
        private Rect checkboxRect;
        private boolean isRandomColorMode;
        private String color;
        private User user;
        private List<CourseDto> courseDtoList;

        public UserTimetable(List<CourseDto> courseDtoList) {
            user = new User();
            this.courseDtoList = courseDtoList;
            isRandomColorMode = true;
        }

        public UserTimetable(User user, List<CourseDto> courseDtoList) {
            this.user = user;
            this.courseDtoList = courseDtoList;
            isRandomColorMode = true;
        }

        public UserTimetable(User user, List<CourseDto> courseDtoList, String color) {
            this.user = user;
            this.courseDtoList = courseDtoList;
            this.color = color;

            isRandomColorMode = false;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public List<CourseDto> getCourseDtoList() {
            return courseDtoList;
        }

        public void setCourseDtoList(List<CourseDto> courseDtoList) {
            this.courseDtoList = courseDtoList;
        }
    }
}
