import styled from 'styled-components';
import palette from '../../lib/styles/palette';

const CamportTemplateBlock = styled.div`
  position: absolute;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
  background: ${palette.gray[2]};
  /* flex로 내부 내용 중앙 정렬 */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

/* 흰색 박스 */
const WhiteBox = styled.div`
  .field-area {
    display: block;
    padding-bottom: 2rem;
    text-align: center;
    font-weight: bold;
    letter-spacing: 2px;
  }
  box-shadow: 0 0 8px rgba(0, 0, 0, 0.025);
  padding: 2rem;
  width: 1080px;
  background: white;
  border-radius: 2px;
`;

const CamportContent = ({ children }) => {

    return (
        <CamportTemplateBlock>
            <WhiteBox>
                <div className="field-area">
                    <ul>
                        Camport Main
                    </ul>
                </div>
                {children}
            </WhiteBox>
        </CamportTemplateBlock>
    );
};

export default CamportContent;