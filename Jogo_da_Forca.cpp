#include <windows.h>
#include <cstdint>

int main() {
	
    LPCWSTR vbsFile = L"Jogo_da_Forca.vbs";
    
    HINSTANCE result = ShellExecuteW(NULL, L"open", vbsFile, NULL, NULL, SW_HIDE);
    
    if (reinterpret_cast<intptr_t>(result) > 32) {
        return 0;
    } else {
        return -1;
    }
    
}