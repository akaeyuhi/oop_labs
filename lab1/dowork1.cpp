#include "framework.h"
#include "resource1.h"
#include "dowork1.h"

static WCHAR buf[256];

static INT_PTR CALLBACK Work1(HWND hDlg, UINT message, WPARAM wParam, LPARAM lParam)
{
    UNREFERENCED_PARAMETER(lParam);
    switch (message)
    {
    case WM_INITDIALOG:
        return (INT_PTR)TRUE;

    case WM_COMMAND:
        if (LOWORD(wParam) == IDOK)
        {
            GetDlgItemTextW(hDlg, IDC_EDIT1, buf, 255);
            EndDialog(hDlg, 1);
            return (INT_PTR)TRUE;
        }
        else if (LOWORD(wParam) == IDCANCEL)
        { 
            EndDialog(hDlg, 0);
            return (INT_PTR)TRUE;
        }
        break;
    }
    return (INT_PTR)FALSE;
}

int DoWork1(HWND hWnd, HINSTANCE hInst, WCHAR *dest) 
{
    DialogBox(hInst, MAKEINTRESOURCE(IDD_DIALOG1), hWnd, Work1);
    lstrcpyW(dest, buf);
    return 1;
}