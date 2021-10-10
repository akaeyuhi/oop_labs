#pragma warning(disable : 4996) 
#include "framework.h"
#include "resource3.h"
#include "dowork2.h"


static WCHAR buf[256];
static WCHAR res[256];


static INT_PTR CALLBACK Work2(HWND hDlg, UINT message, WPARAM wParam, LPARAM lParam)
{
    UNREFERENCED_PARAMETER(lParam);
    static HWND hWndScroll;
    static HWND hWndText;
    static int pos;
    static SCROLLINFO sc;
    sc.cbSize = sizeof(sc);
    sc.fMask = SIF_ALL;
    sc.nMin = 1; 
    sc.nMax = 100;
    switch (message)
    {
        case WM_INITDIALOG:
            return (INT_PTR)TRUE;

        case WM_HSCROLL:
            hWndScroll = GetDlgItem(hDlg, ID_SCROLLBAR);
            hWndText = GetDlgItem(hDlg, IDC_TEXT);
            if (hWndScroll == NULL) return EndDialog(hDlg, 0);
            pos = GetScrollPos(hWndScroll, SB_CTL);
            switch (LOWORD(wParam))
            { 
                case SB_LINELEFT:
                    pos--;
                    break;
                case SB_LINERIGHT:
                    pos++;
                    break;
                case SB_THUMBPOSITION:
                    pos = HIWORD(wParam);
                    break;
                case SB_THUMBTRACK:
                    pos = HIWORD(wParam);
                    break;
                default: break;
            }
            sc.nPos = pos;
            SetScrollInfo(hWndScroll, SB_CTL, &sc, TRUE);
            _itow(pos, buf, 10);
            swprintf(res, size_t(res), L"Result: %s", buf);
            SetWindowTextW(hWndText, res);
            break;
        case WM_COMMAND:
            if (LOWORD(wParam) == IDCANCEL || LOWORD(wParam) == IDOK)
            {
                EndDialog(hDlg, 1);
                return (INT_PTR)TRUE;
            }
            break;
        default: break;
    }
    return (INT_PTR)FALSE;
}

int DoWork2(HWND hWnd, HINSTANCE hInst, WCHAR* dest)
{
    DialogBox(hInst, MAKEINTRESOURCE(IDD_DIALOG1), hWnd, Work2);
    lstrcpyW(dest, buf);
    return 1;
}